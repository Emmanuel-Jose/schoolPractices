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


// Web Workers are a simple means for web content to run scripts in background threads. 
// create each worker and pass as an parameter the script to run
const firstThread = new Worker( './firstThread.js' )
const secondThread = new Worker( './secondThread.js' )
const thirdThread = new Worker( './thirdThread.js' )
const fourthThread = new Worker( './fourthThread.js' )

// we pass the matrix to each thread though the postMessage method
firstThread.postMessage( matrixArray ); // left to right
secondThread.postMessage( matrixArray ); // right to left
thirdThread.postMessage( matrixArray ); // top to bottom
fourthThread.postMessage( matrixArray ); // bottom to top