package com.joebrooks.mapshotserver.manual;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manual")
public class ManualController {

    @GetMapping()
    public String manual(){
        return "manual/index";
    }

    @GetMapping("/page")
    public String changePage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNumber){
        String pageName = "";

        switch (pageNumber){
            case 1:
                pageName = "search-coor";
                break;
            case 2:
                pageName = "range";
                break;
            case 3:
                pageName = "map-type";
                break;
            case 4:
                pageName = "select-output";
                break;
            case 5:
                pageName = "options";
                break;
            case 6:
                pageName = "start-capture";
                break;
            default:
                break;
        }

        return "fragment/manual/" + pageName;
    }
}
