onmessage = function( { data: matrix } ) {

    // right to left

    matrix.forEach( matrixArray => {

        const joinedArray = matrixArray.reverse().join('')
        const isHelloPresent = joinedArray.includes( 'hello' )

        if ( isHelloPresent ) {
            console.log( 'hello left to right' )
        }
    })

}