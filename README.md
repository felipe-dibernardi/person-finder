# Welcome

This is an application to store locations and find people within radius of a coordinate

The decisions taken for this project are shown below:
- Use of Domain Driven Design with a Person entity and a Location value object
- Business logic (boundary coordinates and distance in KM) located within the Domain, without any framework wrapping. That allows for more flexibility if any change in framework is needed.
- Full business logic isolation, how data is persisted (database) or sent/received (controller) is of no concern of this layer
- Database entities separated from domain entities allowing us to both use any kind of database we want and model its entities however we want without worrying about business logic changes.
- DTOs separated from domain entities to allow us to send exactly the fields needed to whoever is consuming our API, specially important when dealing with mobile consumers with restricted bandwidth

Next steps for the project:
- Improve on test coverage
