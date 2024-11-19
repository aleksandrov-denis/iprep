document.addEventListener('DOMContentLoaded', function () {
    const canvas = document.getElementById('radar');
    const ctx = canvas.getContext('2d');
    const radarCenter = { x: canvas.width / 2, y: canvas.height / 2 };
    const radarRadius = canvas.width / 2 - 20;
    const generateTargetButton = document.getElementById('generate-target-btn');
    const eliminateTargetsButton = document.getElementById('eliminate-targets-btn')
    const displayTargetsButton = document.getElementById('display-targets-btn')
    const minSpeedInput = document.getElementById('min-speed-input')
    const consoleOutput = document.getElementById('output-area')

    // Function to draw the radar circle and grid
    function drawRadar() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
        ctx.arc(radarCenter.x, radarCenter.y, radarRadius, 0, 2 * Math.PI);
        ctx.strokeStyle = '#00FF00';
        ctx.stroke();

        // Draw grid
        for (let i = 1; i <= 3; i++) {
            ctx.beginPath();
            ctx.arc(radarCenter.x, radarCenter.y, (radarRadius / 3) * i, 0, 2 * Math.PI);
            ctx.strokeStyle = 'rgba(0, 255, 0, 0.3)';
            ctx.stroke();
        }
    }

    // Function to draw a target on the radar
    function drawTarget(target) {
        ctx.beginPath();
        const targetX = radarCenter.x + target.position[0] * radarRadius;
        const targetY = radarCenter.y + target.position[1] * radarRadius;
        ctx.arc(targetX, targetY, 5, 0, 2 * Math.PI);
        ctx.fillStyle = 'red';
        ctx.fill();
    }

    // Function to update the radar with targets
    function updateRadar() {
        fetch('http://localhost:8080/radar/getTargets', {
            method: 'GET'
        })
        .then(response => response.json())
        .then(targets => {
            drawRadar();
            targets.forEach(target => drawTarget(target));
        })
        .catch(error => console.error('Error fetching targets:', error));
    }

    // Update the radar every 1 second
    setInterval(updateRadar, 1000);

    // Function to generate a new target when the button is clicked
    generateTargetButton.addEventListener('click', function () {
        fetch('http://localhost:8080/radar/generateTarget', {
            method: 'POST'
        })
        .then(target => {
            updateRadar();
        })
        .catch(error => console.error('Error generating target:', error));
    });

    // Function to eliminate all targets when button is clicked
    eliminateTargetsButton.addEventListener('click', function () {
            fetch('http://localhost:8080/radar/eliminateTargets', {
                method: 'DELETE'
            })
            .then(target => {
                updateRadar();
            })
            .catch(error => console.error('Error eliminating targets:', error));
    });

    // Function to fetch and display targets with speed greater than minSpeed
    displayTargetsButton.addEventListener('click', function () {
        const minSpeed = parseFloat(minSpeedInput.value);

        consoleOutput.innerHTML = "";

        if (isNaN(minSpeed)) {
            consoleOutput.innerHTML += "Please enter a valid minimum speed.\n";
            return;
        }

        fetch(`http://localhost:8080/radar/displayTargetsGT?minSpeed=${minSpeed}`, {
            method: 'GET'
        })
        .then(response => response.json())
        .then(logs => {
            logs.forEach(log => {
                consoleOutput.innerHTML += log + "<br>";
            });
            consoleOutput.scrollTop = consoleOutput.scrollHeight;
        })
        .catch(error => console.error('Error fetching targets:', error));
    });
});