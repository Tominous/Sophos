package me.refrac.sophos.handlers.checks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;

public class AutoCorrect extends Check
implements Listener {
    private final Core plugin;

    public static AutoCorrect autocorrect;
    
    public AutoCorrect(Core plugin) {
	  	super("AutoCorrect", "AutoCorrect", plugin);
	    this.plugin = plugin;
    }

    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onAutoCorrectEvent(AsyncPlayerChatEvent chatEvent) {
        if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
            if (chatEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
                return;
            }
            String message = chatEvent.getMessage();
            String[] messageSplit = message.split(" ");
            String newMessage = "";
            String[] array = messageSplit;
            int length = array.length;
            int i = 0;
            while (i < length) {
                String completedWord = array[i];
                completedWord = this.autoCorrect(completedWord);
                newMessage = String.valueOf(String.valueOf(String.valueOf(newMessage))) + completedWord + " ";
                ++i;
            }
            String subString = newMessage.substring(0, 1);
            chatEvent.setMessage(String.valueOf(String.valueOf(String.valueOf(subString))) + newMessage.substring(1));
        }
    }

    public String autoCorrect(String completedWord) {
        if (completedWord.startsWith("cant")) {
            completedWord = "can't";
        } else if (completedWord.startsWith("wouldnt")) {
            completedWord = "wouldn't";
        } else if (completedWord.startsWith("wont")) {
            completedWord = "won't";
        } else if (completedWord.startsWith("couldnt")) {
            completedWord = "couldn't";
        } else if (completedWord.startsWith("didnt")) {
            completedWord = "didn't";
        } else if (completedWord.startsWith("doesnt")) {
            completedWord = "doesn't";
        } else if (completedWord.startsWith("shouldnt")) {
            completedWord = "shouldn't";
        } else if (completedWord.startsWith("wasnt")) {
            completedWord = "wasn't";
        } else if (completedWord.startsWith("werent")) {
            completedWord = "weren't";
        } else if (completedWord.startsWith("anyways")) {
            completedWord = "anyway";
        } else if (completedWord.startsWith("wierd")) {
            completedWord = "weird";
        } else if (completedWord.startsWith("alot")) {
            completedWord = "a lot";
        } else if (completedWord.startsWith("abberration")) {
            completedWord = "aberration";
        } else if (completedWord.startsWith("acheive")) {
            completedWord = "achieve";
        } else if (completedWord.startsWith("adress")) {
            completedWord = "address";
        } else if (completedWord.startsWith("alterior")) {
            completedWord = "ulterior";
        } else if (completedWord.startsWith("athiest")) {
            completedWord = "atheist";
        } else if (completedWord.startsWith("begining")) {
            completedWord = "beginning";
        } else if (completedWord.startsWith("beleive")) {
            completedWord = "believe";
        } else if (completedWord.startsWith("caucasion")) {
            completedWord = "caucasian";
        } else if (completedWord.startsWith("cemetary")) {
            completedWord = "cemetry";
        } else if (completedWord.startsWith("committmeent")) {
            completedWord = "commitment";
        } else if (completedWord.startsWith("concieve")) {
            completedWord = "conceive";
        } else if (completedWord.startsWith("copywrite")) {
            completedWord = "copyright";
        } else if (completedWord.startsWith("dalmation")) {
            completedWord = "dalmatian";
        } else if (completedWord.startsWith("color")) {
            completedWord = "colour";
        } else if (completedWord.startsWith("desireable")) {
            completedWord = "desirable";
        } else if (completedWord.startsWith("diarhea")) {
            completedWord = "diarrhoea";
        } else if (completedWord.startsWith("dissapoint")) {
            completedWord = "disappoint";
        } else if (completedWord.startsWith("dispell")) {
            completedWord = "dispel";
        } else if (completedWord.startsWith("embarass")) {
            completedWord = "embarrass";
        } else if (completedWord.startsWith("facist")) {
            completedWord = "fascist";
        } else if (completedWord.startsWith("febuary")) {
            completedWord = "february";
        } else if (completedWord.startsWith("fivety")) {
            completedWord = "fifty";
        } else if (completedWord.startsWith("fluoroscent")) {
            completedWord = "fluorescent";
        } else if (completedWord.startsWith("flouride")) {
            completedWord = "fluoride";
        } else if (completedWord.startsWith("forteen")) {
            completedWord = "fourteen";
        } else if (completedWord.startsWith("fourty")) {
            completedWord = "forty";
        } else if (completedWord.startsWith("freind")) {
            completedWord = "friend";
        } else if (completedWord.startsWith("geneology")) {
            completedWord = "genealogy";
        } else if (completedWord.startsWith("goverment")) {
            completedWord = "government";
        } else if (completedWord.startsWith("grammer")) {
            completedWord = "grammar";
        } else if (completedWord.startsWith("hampster")) {
            completedWord = "hamster";
        } else if (completedWord.startsWith("harrass")) {
            completedWord = "harass";
        } else if (completedWord.startsWith("hemorage")) {
            completedWord = "haemorrhage";
        } else if (completedWord.startsWith("heros")) {
            completedWord = "heroes";
        } else if (completedWord.startsWith("hight")) {
            completedWord = "height";
        } else if (completedWord.startsWith("hygeine")) {
            completedWord = "hygiene";
        } else if (completedWord.startsWith("hypocracy")) {
            completedWord = "hypocricy";
        } else if (completedWord.startsWith("independance")) {
            completedWord = "independence";
        } else if (completedWord.startsWith("inate")) {
            completedWord = "innate";
        } else if (completedWord.startsWith("innoculate")) {
            completedWord = "inoculate";
        } else if (completedWord.startsWith("intresting")) {
            completedWord = "interesting";
        } else if (completedWord.startsWith("juge")) {
            completedWord = "judge";
        } else if (completedWord.startsWith("knowlege")) {
            completedWord = "knowledge";
        } else if (completedWord.startsWith("libary")) {
            completedWord = "library";
        } else if (completedWord.startsWith("lightening")) {
            completedWord = "lightning";
        } else if (completedWord.startsWith("managable")) {
            completedWord = "manageable";
        } else if (completedWord.startsWith("millenium")) {
            completedWord = "millennium";
        } else if (completedWord.startsWith("mischievious")) {
            completedWord = "mischievous";
        } else if (completedWord.startsWith("mispell")) {
            completedWord = "misspell";
        } else if (completedWord.startsWith("monestary")) {
            completedWord = "monastery";
        } else if (completedWord.startsWith("monkies")) {
            completedWord = "monkeys";
        } else if (completedWord.startsWith("morgage")) {
            completedWord = "mortgage";
        } else if (completedWord.startsWith("mountian")) {
            completedWord = "mountain";
        } else if (completedWord.startsWith("neccessary")) {
            completedWord = "necessary";
        } else if (completedWord.startsWith("neice")) {
            completedWord = "niece";
        } else if (completedWord.startsWith("nickle")) {
            completedWord = "nickel";
        } else if (completedWord.startsWith("nineth")) {
            completedWord = "ninth";
        } else if (completedWord.startsWith("ninty")) {
            completedWord = "ninety";
        } else if (completedWord.startsWith("noone")) {
            completedWord = "no one";
        } else if (completedWord.startsWith("noticable")) {
            completedWord = "noticeable";
        } else if (completedWord.startsWith("occured")) {
            completedWord = "occurred";
        } else if (completedWord.startsWith("ocurence")) {
            completedWord = "occurrence";
        } else if (completedWord.startsWith("oppurtunity")) {
            completedWord = "opportunity";
        } else if (completedWord.startsWith("opthamologist")) {
            completedWord = "ophthalmologist";
        } else if (completedWord.startsWith("paralell")) {
            completedWord = "parallel";
        } else if (completedWord.startsWith("parallell")) {
            completedWord = "parallel";
        } else if (completedWord.startsWith("pasttimee")) {
            completedWord = "pastime";
        } else if (completedWord.startsWith("pavillion")) {
            completedWord = "pavilion";
        } else if (completedWord.startsWith("peice")) {
            completedWord = "piece";
        } else if (completedWord.startsWith("percieve")) {
            completedWord = "perceive";
        } else if (completedWord.startsWith("perserverance")) {
            completedWord = "perseverance";
        } else if (completedWord.startsWith("persue")) {
            completedWord = "pursue";
        } else if (completedWord.startsWith("posesesion")) {
            completedWord = "possession";
        } else if (completedWord.startsWith("potatoe")) {
            completedWord = "potato";
        } else if (completedWord.startsWith("preceeding")) {
            completedWord = "preceding";
        } else if (completedWord.startsWith("pronounciation")) {
            completedWord = "pronunciation";
        } else if (completedWord.startsWith("privelige")) {
            completedWord = "privilege";
        } else if (completedWord.startsWith("publically")) {
            completedWord = "publicly";
        } else if (completedWord.startsWith("quew")) {
            completedWord = "queue";
        } else if (completedWord.startsWith("rasberry")) {
            completedWord = "raspberry";
        } else if (completedWord.startsWith("recieve")) {
            completedWord = "receive";
        } else if (completedWord.startsWith("reccomend")) {
            completedWord = "recommend";
        } else if (completedWord.startsWith("rediculous")) {
            completedWord = "ridiculous";
        } else if (completedWord.startsWith("reguardless")) {
            completedWord = "regardless";
        } else if (completedWord.startsWith("rythm")) {
            completedWord = "rhythm";
        } else if (completedWord.startsWith("shedule")) {
            completedWord = "schedule";
        } else if (completedWord.startsWith("seige")) {
            completedWord = "siege";
        } else if (completedWord.startsWith("sentance")) {
            completedWord = "sentence";
        } else if (completedWord.startsWith("seperate")) {
            completedWord = "separate";
        } else if (completedWord.startsWith("sieze")) {
            completedWord = "seize";
        } else if (completedWord.startsWith("sincerly")) {
            completedWord = "sincerely";
        } else if (completedWord.startsWith("speach")) {
            completedWord = "speech";
        } else if (completedWord.startsWith("stragedy")) {
            completedWord = "strategy";
        } else if (completedWord.startsWith("supercede")) {
            completedWord = "supersede";
        } else if (completedWord.startsWith("suprise")) {
            completedWord = "surprise";
        } else if (completedWord.startsWith("thier")) {
            completedWord = "their";
        } else if (completedWord.startsWith("tomorow")) {
            completedWord = "tomorrow";
        } else if (completedWord.startsWith("tounge")) {
            completedWord = "tongue";
        } else if (completedWord.startsWith("uneform")) {
            completedWord = "uniform";
        } else if (completedWord.startsWith("vaccuum")) {
            completedWord = "vacuum";
        } else if (completedWord.startsWith("vegeterian")) {
            completedWord = "vegetarian";
        } else if (completedWord.startsWith("writen")) {
            completedWord = "written";
        } else if (completedWord.startsWith("writting")) {
            completedWord = "writing";
        } else if (completedWord.startsWith("calender")) {
            completedWord = "calendar";
        } else if (completedWord.startsWith("comming")) {
            completedWord = "coming";
        } else if (completedWord.startsWith("softwares")) {
            completedWord = "software";
        } else if (completedWord.startsWith("thats")) {
            completedWord = "that's";
        } else if (completedWord.startsWith("u")) {
            completedWord = "you";
        }
        return completedWord;
    }
}