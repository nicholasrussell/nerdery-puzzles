# golf

## Usage
Run with

`lein launch <strings to sort>`

or

`lein run -m user/m <strings to sort>`

## Notes
In my first iteration, I was using nil because it's shorter than false and 1 in place of true.

Now, I'm using peek / pop with vectors instead of first / rest since they're shorter, and for vectors, peek looks at the end of the list, which is beneficial for reduce.

Using reduce to get rid of multiple looping constructs including a couple of recur calls.

Current iteration cannot rename any methods since they're not used enough to justify it.

The actual sort takes place here:

`(reduce #(if(>(.compareToIgnoreCase(str(peek %))(str %2))0)(conj(pop %)%2(peek %))(conj % %2))[]l)`

This portion handles "done" checking:
`(if(= l k)l(recur...))`

If the list that we passed to reduce is the same as what came out of reduce, nothing
was swapped and we can return the sorted list.
