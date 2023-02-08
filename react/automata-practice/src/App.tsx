import {SelectAutomata} from "./components/SelectAutomata";


function App(): JSX.Element{

    return (
        <>
            <main className='w-screen h-screen flex flex-col justify-center items-center gap-y-6 bg-neutral-900'>
                <SelectAutomata />
            </main>
        </>
    )

}
export default App
