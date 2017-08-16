import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Brewery;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
/**
 * Created by Guest on 8/16/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: delete all tasks


        //get: show new task form
        get("/tasks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form


        //get: show all tasks


        //get: show an individual task


        //get: show a form to update a task


        //task: process a form to update a task


        //get: delete an individual task
    }
}
