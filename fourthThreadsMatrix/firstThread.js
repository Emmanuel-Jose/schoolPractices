onmessage = function( { data: matrix } ) {

    // left to right

    matrix.forEach( matrixArray => {

        const joinedArray = matrixArray.join('')
        const isHelloPresent = joinedArray.includes( 'hello' )

        if ( isHelloPresent ) {
            console.log( 'hello right to left' )
        }
    })

}