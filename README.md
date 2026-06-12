# Jokes Backend

Generates Egyptian Arabic jokes using Gemini Flash AI.

## Endpoint

GET /AmrAshraf/joke?word=YOUR_WORD_HERE


### Example Request
GET /AmrAshraf/joke?word=مبرمج

### Example Response
{
"word": "مبرمج",
"joke": "...",
"dialect": "Egyptian Arabic",
"requestId": "abc123"
}

## Tech Stack

- Kotlin
- Spring Boot 4
- JDK 25
- Clean Architecture
- Gemini API
- Docker
