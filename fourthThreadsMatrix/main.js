// concurrent program that search the word "hello" in a matrix
const matrixArray = [ 
    [ 'q', 'h', 'e', 'l', 'l', 'o', 'u', 'i', 'o', 'p' ],
    [ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' ],
    [ 'h', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'q' ],
    [ 'e', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'o', 'z' ],
    [ 'l', 'e', 'r', 'h', 'e', 'l', 'l', 'o', 'l', 'q' ],
    [ 'l', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' ],
    [ 'o', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'e', 'q' ],
    [ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'h', 'z' ],
    [ 'w', 'e', 'o', 'l', 'l', 'e', 'h', 'o', 'p', 'q' ],
    [ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' ] 
]

// const firstThread = new Worker( 'firstThread.js' )

// firstThread.postMessage( matrixArray, word )
// console.log( 'firstThread started' )

const firstThread = new Worker( './firstThread.js' )
const secondThread = new Worker( './secondThread.js' )
const thirdThread = new Worker( './thirdThread.js' )
const fourthThread = new Worker( './fourthThread.js' )

firstThread.postMessage( matrixArray ); // left to right
secondThread.postMessage( matrixArray ); // right to left
thirdThread.postMessage( matrixArray ); // top to bottom
fourthThread.postMessage( matrixArray ); // left to right