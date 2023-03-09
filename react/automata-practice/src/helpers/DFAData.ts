import {DFA} from '../types/types';


export const DFA1: DFA = {
    startState: 'q1',
    finalStates: [ 'q6' ],
    transitions: {
        q1: {
            0: 'q2',
        },
        q2: {
            0: 'q2',
            1: 'q3',
        },
        q3: {
            0: 'q3',
            1: 'q4'
        },
        q4: {
            0: 'q5'
        },
        q5: {
            0: 'q5',
            1: 'q6'
        }
    }
}


export const DFA2: DFA = {
    startState: 'q1',
    finalStates: [ 'q4' ],
    transitions: {
        q1: {
            0: 'q1',
            1: 'q2'
        },
        q2: {
            0: 'q2',
            1: 'q3',
            ' ': 'q1'
        },
        q3: {
            0: 'q4',
            1: 'q3'
        }
    }
}


export const DFA3: DFA = {
    startState: 'q1',
    finalStates: [ 'q3' ],
    transitions: {
        q1: {
            a: 'q2',
            ' ': 'q3'
        },
        q2: {
            a: 'q2',
            b: 'q3',
        },
        q3: {
            ' ': 'q1'
        }
    }
}


export const DFA4: DFA = {
    startState: 'q1',
    finalStates: [ 'q6' ],
    transitions: {
        q1: {
            a: 'q2',
            ' ': 'q3'
        },
        q2: {
            a: 'q2',
            b: 'q3',
        },
        q3: {
            a: 'q4',
            ' ': 'q1'
        },
        q4: {
            b: 'q5',
        },
        q5: {
            b: 'q6',
        }
    }
}


export const DFA5: DFA = {
    startState: 'q1',
    finalStates: [ 'q1', 'q2' ],
    transitions: {
        q1: {
            1: 'q2',
            ' ': 'q1'
        },
        q2: {
            1: 'q1',
        }
    }
}


export const DFA6: DFA = {
    startState: 'q1',
    finalStates: [ 'q7' ],
    transitions: {
        q1: {
            a: 'q2',
        },
        q2: {
            a: 'q3',
        },
        q3: {
            ' ': 'q1',
            b: 'q4',
        },
        q4: {
            b: 'q5',
        },
        q5: {
            b: 'q6',
            ' ': 'q3'
        },
        q6: {
            b: 'q6',
            a: 'q7'
        }
    }
}


export const DFA7: DFA = {
    startState: 'q1',
    finalStates: [ 'q11' ],
    transitions: {
        q1: {
            a: 'q2',
        },
        q2: {
            a: 'q3',
        },
        q3: {
            a: 'q4',
            ' ': 'q2'
        },
        q4: {
            b: 'q5',    
        },
        q5: {
            b: 'q6',
            ' ': 'q4'
        },
        q6: {
            a: 'q7',
        },
        q7: {
            a: 'q7',
            b: 'q8',
        },
        q8: {
            b: 'q9',
        },
        q9: {
            b: 'q10',
            ' ': 'q1'
        },
        q10: {
            b: 'q11',
        },
        q11: {
            a: 'q11',
            b: 'q11'
        }
    }
}