const automata = {
    initialState: 0,
    finalStates: 3,
    transitions: [
        { from: 0, to: 1, symbol: 0 },
        { from: 2, to: 2, symbol: 0 },
        { from: 2, to: 3, symbol: 1 },
    ]
}

value = '000001'

const checkAutomata = ( automata, value ) => {

    const nums = value.split( '' ).map( letter => parseInt( letter ) );
    const firstSymbol = nums[ 0 ];
    const lastSymbol = nums[ nums.length - 1 ];
    const middleSymbols = nums.slice( 1, -1 );

    if (firstSymbol !== automata.transitions[0].symbol) return false;
    if (lastSymbol !== automata.transitions[2].symbol) return false;
    if (middleSymbols.length === 0) return true;
    return middleSymbols.every(symbol => symbol === automata.transitions[1].symbol);

}

console.log( checkAutomata( automata, value ) );