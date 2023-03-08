import {DFA} from '../types/types';

// export const DFA1: DFA = {
//     startState: 'q0',
//     finalStates: [ 'q3' ],
//     transitions: {
//         q0: {
//             a: 'q1',
//             b: 'q2',
//             c: 'q3'
//         },
//         q1: {
//             a: 'q2',
//             b: 'q3',
//             c: 'q1'
//         },
//         q2: {
//             a: 'q3',
//             b: 'q1',
//             c: 'q3'
//         }
//     }
// }

// export const DFA2: DFA = {
//     startState: 'q0',
//     finalStates: [ 'q3', 'q4', 'q6', 'q8' ],
//     transitions: {
//         q0: {
//             a: 'q4',
//             c: 'q1'
//         },
//         q1: {
//             c: 'q3'
//         },
//         q4: {
//             a: 'q4',
//             b: 'q8',
//             c: 'q1'
//         },
//         q6: {
//             b: 'q8',
//         },
//         q8: {
//             b: 'q8',
//         }
//     }
// }

// export const DFA3: DFA = {
//     startState: 'q0',
//     finalStates: [ 'q7', 'q8' ],
//     transitions: {
//         q0: {
//             a: 'q7',
//         },
//         q7: {
//             a: 'q7',
//             b: 'q8',
//         },
//         q8: {
//             b: 'q8',
//             c: 'q4'
//         },
//         q4: {
//             b: 'q8',
//             c: 'q4'
//         }
//     }
// }



export const DFA3: DFA = {
    startState: 'q1',
    finalStates: [ 'q1' ],
    transitions: {
        q1: {
            a: 'q1',
            b: 'q1',
        }
    }
}


export const DFA5: DFA = {
    startState: 'q1',
    finalStates: [ 'q1', 'q2' ],
    transitions: {
        q1: {
            1: 'q2',
        },
        q2: {
            1: 'q1',
        }
    }
}


// export const DFA6: DFA = {
//     startState: 'q1',
//     finalStates: [ 'q7' ],
//     transitions: {
//         q1: {
//             a: 'q2',
//         },
//         q2: {
//             a: 'q3',
//         },
//         q3: {
//             b: 'q4',
//             "": 'q1'
//         },
//         q4: {
//             b: 'q5',
//         },
//         q5: {
//             b: 'q6',
//         },
//         q6: {
//             a: 'q7',
//             "": 'q5'
//         }
//     }
// }