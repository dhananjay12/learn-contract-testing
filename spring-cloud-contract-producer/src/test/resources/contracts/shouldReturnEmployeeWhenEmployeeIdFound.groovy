import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description("When a POST request with a User is made, the created user's ID is returned")
  request {
    method 'GET'
    url '/employee/1'  
  }
 response {
    status 200
body("""
  {
    "id": "1",
    "fname": "Jane",
    "lname": "Doe",
    "salary": "123000.00",
    "gender": "M"
  }
  """)
    headers {
      contentType(applicationJson())
    }
  }
}