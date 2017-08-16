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
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        //get: delete all tasks

        get("/brewery/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Brewery.clearAllTasks();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show new task form
        get("/brewery/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "brewery-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/brewery/new", (request, response) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();

            String description = request.queryParams("description");
            Brewery newBrewery = new Brewery(description);
            model.put("brewery", newBrewery);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all tasks
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Brewery> brewery = Brewery.getAll();
            model.put("brewery", brewery);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual task
        get("/brewery/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBreweryToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Brewery foundBrewery = Brewery.findById(idOfBreweryToFind); //use it to find task
            model.put("brewery", foundBrewery); //add it to model for template to display
            return new ModelAndView(model, "task-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/brewery/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBreweryToEdit = Integer.parseInt(req.params("id"));
            Brewery editBrewery = Brewery.findById(idOfBreweryToEdit);
            model.put("editBrewery", editBrewery);
            return new ModelAndView(model, "brewery-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/brewery/:id/update", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("description");
            int idOfBreweryToEdit = Integer.parseInt(req.params("id"));
            Brewery editBrewery = Brewery.findById(idOfBreweryToEdit);
            editBrewery.update(newContent);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/brewery/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBreweryToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Brewery deleteBrewery = Brewery.findById(idOfBreweryToDelete); //use it to find task
            deleteBrewery.deleteBrewery();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
