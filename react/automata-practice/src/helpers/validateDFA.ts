import {DFA} from "../types/types";

export const validateDFA = (dfa: DFA, string: string): boolean => {
    let currentState = dfa.startState;
    for (const char of string) {
		if (!dfa.transitions[currentState]) {
			return false;
		}
		const nextState = dfa.transitions[currentState][char];
		if (nextState === undefined) {
			return false;
		}
		currentState = nextState;
    }
    return dfa.finalStates.includes(currentState);
}

