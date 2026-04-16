# Dispense API

## Setup

```sh
cp .env.example .env
```

Fill in the values in `.env`.

## Run

```sh
source .env && ./mvnw spring-boot:run
```

## Contributing

The project uses [Spring Java Format](https://github.com/spring-io/spring-javaformat).

To format your code before committing:

```sh
./mvnw spring-javaformat:apply
```

For more details see CONTRIBUTORS.md

## LLMs 🤖
This project was contributed to by LLMs. Contributors are more than welcome to use them, bearing that:
1. You are wholly responsible for the output.
2. You are able to understand and explain the changes made.
3. Commit messages containing LLM contributed code must contain a co-authorship credit.

### What should I do?
The below guidelines are a good start.
* You **must** write titles/descriptions of issues, pull requests and commits yourself.
  * You may cite LLM output in your writing, provided it doesn't make up your main point.
  * You should be able to answer any questions from maintainers, though we can't really check that.
* You should aim to write documentation yourself.
  * Key documents such as README and CONTRIBUTORS should remain human-written.
  * You may consult an LLM on how to write prompts for AGENTS or any other prompt documents, but you must review them yourself eventually.
  * This doesn't include Javadoc/code comments. Don't let your LLM overcomment though - aim for clear and readable code instead.

### Am I doing it right?
A good rule of thumb is:
- Would you work with the code, if you did not have access to an LLM?
- Do your prompts refer to specific modules/files/methodology, or are they only specifying implementation of a feature?
- Have you checked the outputs?