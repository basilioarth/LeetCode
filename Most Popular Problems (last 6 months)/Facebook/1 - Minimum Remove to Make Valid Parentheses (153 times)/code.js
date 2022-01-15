/**
 * Status: Accepted
 * 62 / 62 test cases passed.
 * Runtime: 123 ms
 * Memory Usage: 52.3 MB
 * Details:
 * - Your runtime is faster than 46.94% of JavaScript online submissions for Minimum Remove to Make Valid Parentheses.
 * - Your memory usage are less than 18.05% of JavaScript online submissions for Minimum Remove to Make Valid Parentheses.
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
            stack.length == 0 ? s[i] = '' : stack.pop();
        }
    }
    
    while(stack.length > 0) {
        letter = stack.pop();
        s.splice(letter.index, 1)
    }
    
    
    let result = '';

    s.forEach((letter) => {
       result = result + letter;
    });
    
    return result;
};
