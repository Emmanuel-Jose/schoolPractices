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

// export const validateDFA = (dfa: DFA, string: string): boolean => {
//     let currentState = dfa.startState;
//     for (const char of string) {
//         if (!dfa.transitions[currentState]) {
//             return false;
//         }
//         // Check for empty string transitions
//         if (dfa.transitions[currentState][""] !== undefined) {
//             currentState = dfa.transitions[currentState][""];
//         }
//         const nextState = dfa.transitions[currentState][char];
//         if (nextState === undefined) {
//             return false;
//         }
//         currentState = nextState;
//     }
//     // Check for empty string transitions from the final state
//     if (dfa.transitions[currentState][""] !== undefined) {
//         currentState = dfa.transitions[currentState][""];
//     }
//     return dfa.finalStates.includes(currentState);
// }

