import Button from "./Button";

export default function Sidebar(props) {
  return (
    <>
      <aside className="w-1/3 px-8 py-16 bg-stone-900 text-stone-50 md:w-72 rounded-r-xl">
        <h1 className="mb-8 font-bold uppercase md:text-xl text-stone-200">
          Your Projects
        </h1>
        <Button onClick={props.addProj}>+ Add Project</Button>
        <ul className="space-y-2 mt-8">
          {props.projectList.map((proj) => {
            let tailwindClasses =
              "w-full text-left px-2 py-1 rounded-sm my-1 hover:text-stone-200 hover:bg-stone-800";

            if (proj.id === props.selectedProjectID) {
              tailwindClasses =
                tailwindClasses + " bg-stone-800 text-stone-200";
            } else {
              tailwindClasses = tailwindClasses + " text-stone-400";
            }

            return (
              <li id={proj.id} key={proj.id}>
                <button
                  className={tailwindClasses}
                  onClick={() => {
                    props.onSelect(proj.id);
                  }}
                >
                  {proj.title}
                </button>
              </li>
            );
          })}
        </ul>
      </aside>
    </>
  );
}
