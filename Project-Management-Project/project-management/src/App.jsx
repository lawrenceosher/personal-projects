import { useState } from "react";
import ProjectInput from "./components/ProjectInput";
import Sidebar from "./components/sidebar";
import NoProjectSelected from "./components/NoProjectSelected";
import ProjectDetails from "./components/ProjectDetails";

function App() {
  const [showingInput, setShowingInput] = useState(undefined);
  const [projectList, setProjectList] = useState([]);
  const [selectedProject, setSelectedProject] = useState({});
  const [tasks, setTasks] = useState([]);

  const onAddTaskHandler = (taskValue) => {
    const newTask = {
      taskText: taskValue,
      taskID: Math.random(),
      projectID: selectedProject.id
    };
    setTasks((prevTasks) => {
      return [...prevTasks, newTask];
    });
  };

  const onDeleteTaskHandler = (taskID) => {
    setTasks((prevTasks) => {
      const keptTasks = prevTasks.filter(
        (filteredTask) => filteredTask.taskID !== taskID
      );
      return [...keptTasks];
    });
  };

  const onAddNewProjectHandler = () => {
    setShowingInput(null);
  };

  const onCancelAddProjectHandler = () => {
    setShowingInput(undefined);
  };

  const onSelectProjectHandler = (projectID) => {
    const selectedProj = projectList.filter(
      (filteredProj) => filteredProj.id == projectID
    )[0];
    setShowingInput(false);
    setSelectedProject(selectedProj);
  };

  const onSaveProjectHandler = (proj) => {
    const newProject = {
      ...proj,
      id: Math.random(),
    };
    setProjectList((prevProjects) => {
      return [...prevProjects, newProject];
    });
    setShowingInput(undefined);
  };

  const onDeleteProjectHandler = () => {
    setProjectList((prevProjects) => {
      const keptProjects = prevProjects.filter(
        (filteredProject) => filteredProject.id !== selectedProject.id
      );
      return [...keptProjects];
    });
    setShowingInput(undefined);
  };

  let shownContent = (
    <ProjectDetails
      onDelete={onDeleteProjectHandler}
      project={selectedProject}
      onAddTask={onAddTaskHandler}
      onDeleteTask={onDeleteTaskHandler}
      taskList={tasks}
    />
  );

  if (showingInput === null) {
    shownContent = (
      <ProjectInput
        cancelProj={onCancelAddProjectHandler}
        saveProject={onSaveProjectHandler}
      />
    );
  } else if (showingInput === undefined) {
    shownContent = <NoProjectSelected addProj={onAddNewProjectHandler} />;
  }

  return (
    <main className="flex gap-8 h-screen my-8">
      <Sidebar
        projectList={projectList}
        addProj={onAddNewProjectHandler}
        onSelect={onSelectProjectHandler}
        selectedProjectID={
          selectedProject.id !== undefined ? selectedProject.id : ""
        }
      />
      {shownContent}
    </main>
  );
}

export default App;
