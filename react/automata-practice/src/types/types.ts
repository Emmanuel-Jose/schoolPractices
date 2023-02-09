export interface InputAutomataProps {
    isAutomataValid: Function;
    automataType: number;
    onChangeMessageDisplayed: Function;
}

export interface DFA {
    startState: string;
    finalStates: string[];
    transitions: {
        [key: string]: {
            [key: string] : string;
        };
    };
}