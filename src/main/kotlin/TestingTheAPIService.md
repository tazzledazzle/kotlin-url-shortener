You can use tools like Postman or cURL to test your API endpoints.

Example cURL Commands

Get all users:

```sh
curl -X GET http://localhost:8080/api/users
```

Get a user by ID:

```sh
curl -X GET http://localhost:8080/api/users/1
```

Create a new user:

```sh
curl -X POST -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}' http://localhost:8080/api/users
```

Update a user:

```sh
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Jane Doe", "email": "jane.doe@example.com"}' http://localhost:8080/api/users/1
```
Delete a user:

```sh
curl -X DELETE http://localhost:8080/api/users/1
```


This will need to be modified in order to facilitate the shortening service
