package routes;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("students/")
public class Student {

    @GET
    public String index() {
        return "Hello";
    }
}
