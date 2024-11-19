# Interview Prep for Java Developer Role

This is an approach I took to prepare for a job interview. I thought that it would be more interesting to refresh my Java skills if I built a project that I could play around with and publish online. The following is a description of the features of the exercise. Enjoy!

## Radar Simulation Project

This project is a radar simulation system where moving targets are visualized on a radar screen. The system allows generating targets, displaying them based on their speed, and interacting with the radar through a web-based UI. Users can filter targets based on their speed and dynamically view targets on the radar.

## Features
- **Radar Visualization**: A radar screen is drawn using the HTML `<canvas>` element, where targets are displayed as moving dots.
- **Dynamic Target Generation**: Targets can be dynamically generated and moved across the radar based on random speeds and directions.
- **Target Filtering by Speed**: Users can set a minimum speed. Targets moving faster than that speed will be displayed in red, and in green otherwise.
- **Console Output**: The system logs information about the targets and their movement if their speed exceeds the speed limit specified by the user. This is displayed in a console below the radar.

## Technologies Used
- **Frontend**:
  - **HTML**: Markup for the structure of the webpage, including the radar canvas and input elements.
  - **CSS**: Styling for the radar, console, buttons, and input fields.
  - **JavaScript (ES6+)**: For frontend logic including fetching data from the backend and updating the radar.
  - **Canvas API**: Used to draw the radar and target positions dynamically.
  
- **Backend**:
  - **Java**:
    - **Spring Boot**: For building the REST API to manage the radar system and interact with the frontend.
    - **Executor Service**: For managing concurrent target processing.
    - **Collections (e.g., `CopyOnWriteArrayList`)**: For handling target data safely in a multithreaded environment.
    - **JAX-RS**: For defining the RESTful web services to interact with the frontend.
  - **Maven**: For dependency management and building the Java project.

## Installation & Setup

### Prerequisites
- Java 11 or higher.
- Maven.
- Node.js and npm (for building and running the frontend).

### Backend Setup (Spring Boot)

1. Clone the repository:
   ```bash
   git clone git@github.com:aleksandrov-denis/iprep.git
   cd iprep
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. The backend will be running on `http://localhost:8080`.

### Frontend Setup (JavaScript)

1. In the frontend directory (same directory as `index.html`), ensure the necessary dependencies are installed:
   ```bash
   npm install
   ```

2. Start a local development server (optional if not deploying to GitHub Pages):
   ```bash
   npm start
   ```

### Deploying to GitHub Pages

To deploy the frontend to GitHub Pages:
1. Ensure all the necessary static files (HTML, CSS, JS) are correctly referenced in your repository.
2. Follow the instructions on the official GitHub Pages site.

## Usage

1. **Generate Targets**: Click the "GENERATE" button to create a new target.
2. **Reset Targets**: Click the "CLEAR" button to remove all targets from the radar.
3. **Filter Targets by Speed**: Use the input field to set a minimum speed and click the "DISPLAY" button to filter and display targets moving faster than the set speed.
4. **Radar**: The radar will update two seconds with the current positions of all active targets.
5. **Console**: The console at the bottom of the page will display the position and speed of every target that is exceeding the speed limit.

## Example Console Output
```
-------------Displaying-Current-Targets-Moving-Faster-Than-20.0-m/s-------------
Target 1 is at position [50.12, -30.45] moving at 25.78m/s
Target 2 is at position [15.23, 10.56] moving at 32.10m/s
```

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.
