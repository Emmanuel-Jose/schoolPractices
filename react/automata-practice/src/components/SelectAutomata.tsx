import {ChangeEvent, useState} from "react";
import {InputAutomata} from "./InputAutomata";


export const SelectAutomata = (): JSX.Element => {

    const [ automataType, setAutomataType ] = useState<number>(0);
    const [ isAutomataValid, setIsAutomataValid ] = useState<boolean>(false);
    const [ isMessageDisplayed, setIsMessageDisplayed ] = useState<boolean>(false);

    const onChangeAutomataValid = (value: boolean): void => {
        setIsAutomataValid(value);
    }

    const onChangeMessageDisplayed = (value: boolean): void => {
        setIsMessageDisplayed(value);
    }

    const onHandleChange = (e: ChangeEvent<HTMLSelectElement>): void => {
        const selectedValue: number = parseInt(e.currentTarget.value);
        setAutomataType(selectedValue);
    }

    const displayMessage = (): JSX.Element => {

        if ( automataType === 0 ) return <></>;
        if ( !isMessageDisplayed ) return <></>;

        if ( isAutomataValid ) {
            return(
                <>
                    <p className='text-white text-2xl font-medium'>
                        {`Automata ${ automataType }: `}
                        <span className='text-green-500'>Correct</span>
                    </p>
                </>
            )
        }

        return(
            <>
                <p className='text-white text-2xl font-medium'>
                    {`Automata ${ automataType }: `}
                    <span className='text-red-500'>Incorrect</span>
                </p>
            </>
        )
    }

    return(
        <>
            {
                displayMessage()
            }

            <section className='w-full flex justify-center items-center'>
                <select
                    name='automataType'
                    id='automataType'
                    value={ automataType }
                    onChange={ (e) => onHandleChange(e)}
                    className='w-5/6 sm:w-3/4 md:w-1/2 h-10 sm:h-12 bg-neutral-800 text-neutral-100 rounded-md focus:outline-none px-2.5'
                >
                    <option value='0'>Select an automata</option>
                    <option value="1">Automata 1</option>
                    <option value="2">Automata 2</option>
                    <option value="3">Automata 3</option>
                    <option value="4">Automata 4</option>
                    <option value="5">Automata 5</option>
                    <option value="6">Automata 6</option>
                    <option value="7">Automata 7</option>
                </select>
            </section>

            <InputAutomata isAutomataValid={ onChangeAutomataValid } automataType={automataType} onChangeMessageDisplayed={ onChangeMessageDisplayed } />
        </>
    );
}