package fr.univrouen.rss22.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	@RestController
public class RSSController {
		
		@GetMapping("/resume")
		public String getListRSSinXML() {
			return "Envoi de la listse des flux RSS";
		}
		
		@GetMapping("/guid")
		public String getRSSinXML(
			@RequestParam(value = "guid") String texte) {
				return("Détail du flux RSS demandé: " + texte);
		}
		
		@GetMapping("/test")
		public String test(
			@RequestParam(value = "nb") int val, 
			@RequestParam(value = "search") String texte) {
				return("Test :" + "guid = " + val + "titre = " + texte);
		}
		
		
}
	
