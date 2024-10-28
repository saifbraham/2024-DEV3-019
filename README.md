# 2024-DEV3-019
Code Kata

# To test Get price in local host after clone project and start server :
http://localhost:8080/api/books/calculatePrice/CleanCode=2,TheCleanCoder=2,CleanArchitecture=2,TestDrivenDevelopmentbyExample=1,WorkingEffectivelyWithLegacyCode=1

# DevelopmentBooks Pricing Model

There is a series of books about software development that have been read by many developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books.

## Available Books

The available books are:

- **Clean Code** (Robert Martin, 2008)
- **The Clean Coder** (Robert Martin, 2011)
- **Clean Architecture** (Robert Martin, 2017)
- **Test Driven Development by Example** (Kent Beck, 2003)
- **Working Effectively With Legacy Code** (Michael C. Feathers, 2004)

## Pricing Rules

The rules for pricing are described below:

- One copy of any book costs **50 EUR**.
- If you buy two different books, you get a **5% discount** on those two books.
- If you buy three different books, you get a **10% discount**.
- If you buy four different books, you get a **20% discount**.
- If you buy all five books, you get a **25% discount**.

**Note:** If you buy, for example, 4 books, of which 3 are different titles, you get a 10% discount on the 3 different books, but the 4th book still costs **50 EUR**.

## Example Shopping Basket

For instance, how much does this basket of books cost?

- 2 copies of the **Clean Code** book
- 2 copies of the **Clean Coder** book
- 2 copies of the **Clean Architecture** book
- 1 copy of the **Test Driven Development by Example** book
- 1 copy of the **Working Effectively With Legacy Code** book

### Price Calculation:

- (4 books) - **20% discount**: Clean Code, Clean Coder, Clean Architecture, Test Driven Development by Example
    - \( (4 * 50 EUR) - 20\% = 160 EUR \)

- (4 books) - **20% discount**: Clean Code, Clean Coder, Clean Architecture, Working Effectively With Legacy Code
    - \( (4 * 50 EUR) - 20\% = 160 EUR \)

### Total:
\[
160 EUR + 160 EUR = 320 EUR
\]

> **Note**: Knowledge is priceless but has a cost!
