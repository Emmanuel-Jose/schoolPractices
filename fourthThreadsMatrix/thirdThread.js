onmessage = function( { data: matrix } ) {

    // top to bottom
    const arrayLength = matrix[0].length

    for ( let index = 0; index < arrayLength; index++ ) {

        const wordsArr = []

        for ( const arr of matrix ) {

            matrixArray = arr

            wordsArr.push( arr[ index ] )
        }

        const joinedArray = wordsArr.join( '' )
        const isHelloPresent = joinedArray.includes( 'hello' )

        if ( isHelloPresent ) console.log( 'hello from top to bottom' )
        
    }


}