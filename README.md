# Valc
A sort of vector calculator/Language. Uses vectors as the primary data type, and supports several operations on vectors including:
- addition/subtraction
- scalar multiplication
- dot product
- cross product
- vector projection
- scalar projection

The program currently supports variable assignment, and allows variables to be used in the aforementioned operations. 

Example of input code:

a := <1,1,1>;

b := <1,2,3>;

a x b

Output:

<1.0, -2.0, 1.0>


# Planned Additions/Changes

- The grammer for expressions is pretty limited right now; in many cases the grammar calls for terminal values where a more general "expression" could be used. Part of the reason for this is that I haven't decided on the order of operations for the vector operators yet. Once I solidify that, I will extend the parser to process longer expressions.

- Currently when processing text, the lexer can read integer and floating point literals for scalar values and vector components. Negative integer and float literals are not yet supported. I will likely handle this directly in the lexer instead of adding a standard unary minus operator.

- I plan to implement constant folding directly in the parser, since only expressions that rely on variables need to wait until runtime for evaluation.

- Some form of branching and looping will be added.
