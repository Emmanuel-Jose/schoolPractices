import {FormEvent, useEffect, useState} from "react";
import {testValue} from "../helpers/testValue";

const regex: RegExp = new RegExp('^[a-z]+$');
const hasOnlyLetters = (value: string): Boolean => regex.test(value);

interface InputAutomataProps {
    isAutomataValid: Function;
    automataType: number;
    onChangeMessageDisplayed: Function;
}

export const InputAutomata = ({ isAutomataValid, automataType, onChangeMessageDisplayed }: InputAutomataProps ): JSX.Element => {

    const [ automataInput, setAutomataInput ] = useState<string>('');
    const [ inputMessage, setInputMessage ] = useState<string>('');

    useEffect(()=> {
        (hasOnlyLetters(automataInput))
            ? setInputMessage('valid string')
            : setInputMessage("invalid string");
        onChangeMessageDisplayed(false);
    }, [ automataInput ])
    
    const onInputChange = ( e: FormEvent<HTMLInputElement> ): void => {
        const inputValue: string = e.currentTarget.value;
        setAutomataInput(inputValue.toLowerCase());
    }

    const onHanldeSubmit = ( e: FormEvent<HTMLFormElement> ): void => {
        e.preventDefault();
        validateAutomata();
        if ( automataInput !== '' ) onChangeMessageDisplayed(true);
        if ( !hasOnlyLetters(automataInput) ) onChangeMessageDisplayed(false);

    }

    const validateAutomata = (): void => {
        const result = testValue(automataInput);
        isAutomataValid(result);
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
                    <p
                        className={`${ ( inputMessage === 'valid string' ) ? 'text-green-500' : 'text-red-500' } text-xs -mt-5`}
                    >{ ( automataInput === '' ) ? '' : inputMessage }</p>


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