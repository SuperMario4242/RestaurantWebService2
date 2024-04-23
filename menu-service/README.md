# menu-service
A simple REST Python + MySQL web service that implements CRUD operations on dish menu database. <br />
Using authors database service as dependence from here: https://github.com/VytenisKaj/LibraryWebService

## How to run it yourself
```bash
git clone https://github.com/Piratux/menu-service
cd menu-service
docker-compose up -d
```

## Documentation
https://documenter.getpostman.com/view/26799355/2s93XsWkQ8

## Test it out
```bash
curl -X GET http://127.0.0.1:5000/dishes
```

## Making modifications
After making edits, project can be rebuilt and restarted as such
```bash
docker-compose build
docker-compose stop service -t 1
docker-compose up -d
```
