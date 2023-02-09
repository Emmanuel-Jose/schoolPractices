import {SelectAutomata} from "./components/SelectAutomata";


function App(): JSX.Element{

    return (
        <>
            <main className='w-screen h-screen flex flex-col justify-center items-center gap-y-6 bg-neutral-900 relative'>
                <SelectAutomata />
                <footer className='w-full flex justify-center items-center absolute bottom-0'>
                    <p className='text-white text-sm font-medium'>Bayona Ortega Jose Emmanuel 7A ISW BIS</p>
                </footer>
            </main>
        </>
    )

}
export default App
