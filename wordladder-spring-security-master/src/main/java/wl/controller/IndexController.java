package wl.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wl.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class.getClass());
    @Resource
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public ModelAndView toAdmin(ModelAndView model,
                                @RequestParam("word1")String word1,
                              @RequestParam("word2")String word2,
                              @RequestParam("filename")String filename){
        Wordladder wl = new Wordladder();
        try {
            logger.info("searching from " + word1 + " to " + word2);
            //model.addObject("ladder1","abc");
            //model.addAttribute("ladder",wl.GetLadder(filename,word1,word2).getLadder());
             model.addObject("ladder",wl.GetLadder(filename,word1,word2).getLadder());
            //return wl.GetLadder(filename, word1, word2);
        }catch(Exception e){
            logger.error("IOException occured");
            //return new FindLadder(1,"IOException occured", new String[]{});
        }
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value="/wordladder",method=RequestMethod.GET)
    public String wordladder() {
        return "wordladder";
    }

    @RequestMapping(value="/wordladder",method = RequestMethod.POST)
    public ModelAndView wordladder_post(ModelAndView model,
                                @RequestParam("word1")String word1,
                                @RequestParam("word2")String word2,
                                @RequestParam("filename")String filename){
        Wordladder wl = new Wordladder();
        try {
            logger.info("searching from " + word1 + " to " + word2);
            //model.addObject("ladder1","abc");
            //model.addAttribute("ladder",wl.GetLadder(filename,word1,word2).getLadder());
            model.addObject("ladder",wl.GetLadder(filename,word1,word2).getLadder());
            //return wl.GetLadder(filename, word1, word2);
        }catch(Exception e){
            logger.error("IOException occured");
            //return new FindLadder(1,"IOException occured", new String[]{});
        }
        model.setViewName("wordladder");
        return model;
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String root() {
        return "home";
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
