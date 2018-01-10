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

- Some form of branching and looping will be added.

- A few types of optimizations, such as simple constant folding, will be added.

