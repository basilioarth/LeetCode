/**
 * Status: Wrong Answer
 * Details:
 * Input: "))(("
 * Output: [")"]
 * Expected: ""
*/ 

/**
 * @param {string} s
 * @return {string}
 */
 var minRemoveToMakeValid = function(s) {
    stack = [];
    s = s.split('');
    
    for(let i=0; i < s.length; i++){
        if(s[i] === '(') {
            stack.push({value: '(', index: i})
        }
        else if(s[i] === ')') {
            if(stack.length == 0) { s.splice(i, 1); i = 0 } else stack.pop();
        }
    }
    
    while(stack.length > 0) {
        letter = stack.pop();
        s.splice(letter.index, 1)
    }
    
    /**
    * Somente para deixar a saída mais apresentável
    *   let result = '';
    *
    *   s.forEach((letter) => {
    *       result = result + letter;
    *   });
    */
    
    return s;
};