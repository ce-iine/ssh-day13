package ssf.iss.day13class.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("/home") 
public class HomeController { //passing data from one page to another using HttpSession
    //save data in one page and get the data when you go to another page

    @GetMapping ("/pagea") //GET: URL only
    public String PageA(Model model, HttpSession session) { //session is a map 
        if (session.getAttribute("myFullName") != null) {
            model.addAttribute("myName", session.getAttribute("myFullName").toString());
        } else {
            model.addAttribute("myName", "There is no session object now");
        }
        return "pagea";
    }

    @PostMapping ("/pagea") //POST: URL + request (header+body) usually when you enter username/password done through post mapping, GET records url 
    public String PageAPost (@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session){
        String myFullName = form.getFirst("fullname");
        System.out.println("My fullname is " + myFullName);

        session.setAttribute("myFullName", myFullName); 

        model.addAttribute("myName", session.getAttribute("myFullName").toString());
        return "pageb";
    }

    @GetMapping("/pageb")
    public String PageB(Model model, HttpSession session) {

        String myFullName = session.getAttribute("myFullName").toString();
        model.addAttribute("myName", myFullName);

        return "pagec";
    }

    @PostMapping("/pagec")
    public String PageC(Model model, HttpSession session) {
        session.invalidate(); // destroy session 
        model.addAttribute("myName", "There is a new session");
        // String myFullName = session.getAttribute("myFullName").toString();
        // model.addAttribute("myName", myFullName);

        return "pagea";
    }
}
