onmessage = function( { data: matrix } ) {

    // top to bottom
    let word = ''
    arrayLength = matrix[0].length

    for ( let index = 0; index < arrayLength.length; index++ ) {
        
        for ( let indexArray = 0; indexArray < matrix.length; indexArray++ ) {
            
            matrixArray = matrix[ indexArray ]
            word = word + matrixArray[ index ]


        }
        
    }

    if( word.includes[ 'hello' ] ) {
        console.log( 'hello top to bottom' )
    }

}