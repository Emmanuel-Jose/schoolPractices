import {FormEvent, useEffect, useState} from "react";
import {InputAutomataProps} from "../types/types";
import {validateDFA} from "../helpers/validateDFA";
import { DFA3, DFA5 } from "../helpers/DFAData";


export const InputAutomata = ({ isAutomataValid, automataType, onChangeMessageDisplayed }: InputAutomataProps ): JSX.Element => {

    const [ automataInput, setAutomataInput ] = useState<string>('');
    // const [ inputMessage, setInputMessage ] = useState<string>('');

    useEffect(()=> {
        setAutomataInput('');
        onChangeMessageDisplayed(false);
    }, [ automataType ])

    useEffect(()=> {
        if ( automataInput === '' ) {
            // if there is no input, don't show the message
            onChangeMessageDisplayed(false);
            return;
        }
    }, [ automataInput ])
    
    const onInputChange = ( e: FormEvent<HTMLInputElement> ): void => {
        const inputValue: string = e.currentTarget.value;
        const valueToCheck = inputValue.toLocaleLowerCase()
        setAutomataInput(valueToCheck);
    }

    const onHanldeSubmit = ( e: FormEvent<HTMLFormElement> ): void => {
        e.preventDefault();
        if ( automataInput === '' ) return;
        validateAutomata();
        if ( automataInput !== '' ) onChangeMessageDisplayed(true);
        // if ( !hasOnlyLetters(automataInput) ) onChangeMessageDisplayed(false);
    }

    const validateAutomata = (): void => {
        switch ( automataType ) {
            // case 1:
            //     const isValidDFA1 = validateDFA( DFA1, automataInput );
            //     isAutomataValid(isValidDFA1);
            //     break;
            // case 2:
            //     const isValidDFA2 = validateDFA( DFA2, automataInput );
            //     isAutomataValid(isValidDFA2);
            //     break;
            case 3:
                const isValidDFA3 = validateDFA( DFA3, automataInput );
                isAutomataValid(isValidDFA3);
                break;
            // case 4:
            //     const isValidDFA4 = validateDFA( DFA4, automataInput );
            //     isAutomataValid(isValidDFA4);
            //     break;
            case 5:
                const isValidDFA5 = validateDFA( DFA5, automataInput );
                isAutomataValid(isValidDFA5);
                break;
            // case 6:
            //     const isValidDFA6 = validateDFA( DFA6, automataInput );
            //     isAutomataValid(isValidDFA6);
            //     break;
        }
    }

    return (
        <>
            <form
                onSubmit={ (e) => onHanldeSubmit(e) }
                className='w-full flex flex-col justify-center items-center gap-y-6'
            >
                    <input
                        type="text"
                        name='automataInput'
                        id='automataInput'
                        value={ automataInput }
                        onChange={ (e) => onInputChange(e) }
                        className='w-5/6 sm:w-3/4 md:w-1/2 h-10 sm:h-12 bg-neutral-800 text-neutral-100 rounded-md focus:outline-none px-2.5'
                        placeholder='Enter your string here...'
                    />
                    {/* <p
                        className={`${ ( inputMessage === 'valid string' ) ? 'text-green-500' : 'text-red-500' } text-xs -mt-5`}
                    >{ ( automataInput === '' ) ? '' : inputMessage }</p> */}


                <button
                    type='submit'
                    className='w-5/6 sm:w-3/4 md:w-1/2 h-10 sm:h-12 text-white bg-fuchsia-700 p-2 rounded-md'
                >
                    Check the string
                </button>

            </form>
        </>
    );
}