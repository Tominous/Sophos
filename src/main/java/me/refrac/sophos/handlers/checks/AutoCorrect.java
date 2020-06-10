package me.refrac.sophos.handlers.checks;

import me.refrac.sophos.Sophos;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AutoCorrect extends Check implements Listener {

    private Sophos sophos;

    public AutoCorrect(Plugin plugin) {
	  	super("AutoCorrect", "AutoCorrect", (Sophos)plugin);
        sophos = (Sophos)plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onAutoCorrectEvent(AsyncPlayerChatEvent chatEvent) {
        if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
            if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
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
                i++;
            }
            String subString = newMessage.substring(0, 1);
            chatEvent.setMessage(String.valueOf(String.valueOf(String.valueOf(subString))) + newMessage.substring(1));
        }
    }

    private String autoCorrect(String completedWord) {
        if (completedWord.equalsIgnoreCase("cant")) {
            completedWord = "can't";
        } else if (completedWord.equalsIgnoreCase("wouldnt")) {
            completedWord = "wouldn't";
        } else if (completedWord.equalsIgnoreCase("wont")) {
            completedWord = "won't";
        } else if (completedWord.equalsIgnoreCase("couldnt")) {
            completedWord = "couldn't";
        } else if (completedWord.equalsIgnoreCase("didnt")) {
            completedWord = "didn't";
        } else if (completedWord.equalsIgnoreCase("doesnt")) {
            completedWord = "doesn't";
        } else if (completedWord.equalsIgnoreCase("shouldnt")) {
            completedWord = "shouldn't";
        } else if (completedWord.equalsIgnoreCase("wasnt")) {
            completedWord = "wasn't";
        } else if (completedWord.equalsIgnoreCase("werent")) {
            completedWord = "weren't";
        } else if (completedWord.equalsIgnoreCase("anyways")) {
            completedWord = "anyway";
        } else if (completedWord.equalsIgnoreCase("wierd")) {
            completedWord = "weird";
        } else if (completedWord.equalsIgnoreCase("alot")) {
            completedWord = "a lot";
        } else if (completedWord.equalsIgnoreCase("abberration")) {
            completedWord = "aberration";
        } else if (completedWord.equalsIgnoreCase("acheive")) {
            completedWord = "achieve";
        } else if (completedWord.equalsIgnoreCase("adress")) {
            completedWord = "address";
        } else if (completedWord.equalsIgnoreCase("alterior")) {
            completedWord = "ulterior";
        } else if (completedWord.equalsIgnoreCase("athiest")) {
            completedWord = "atheist";
        } else if (completedWord.equalsIgnoreCase("begining")) {
            completedWord = "beginning";
        } else if (completedWord.equalsIgnoreCase("beleive")) {
            completedWord = "believe";
        } else if (completedWord.equalsIgnoreCase("caucasion")) {
            completedWord = "caucasian";
        } else if (completedWord.equalsIgnoreCase("cemetary")) {
            completedWord = "cemetry";
        } else if (completedWord.equalsIgnoreCase("committmeent")) {
            completedWord = "commitment";
        } else if (completedWord.equalsIgnoreCase("concieve")) {
            completedWord = "conceive";
        } else if (completedWord.equalsIgnoreCase("copywrite")) {
            completedWord = "copyright";
        } else if (completedWord.equalsIgnoreCase("dalmation")) {
            completedWord = "dalmatian";
        } else if (completedWord.equalsIgnoreCase("color")) {
            completedWord = "colour";
        } else if (completedWord.equalsIgnoreCase("desireable")) {
            completedWord = "desirable";
        } else if (completedWord.equalsIgnoreCase("diarhea")) {
            completedWord = "diarrhoea";
        } else if (completedWord.equalsIgnoreCase("dissapoint")) {
            completedWord = "disappoint";
        } else if (completedWord.equalsIgnoreCase("dispell")) {
            completedWord = "dispel";
        } else if (completedWord.equalsIgnoreCase("embarass")) {
            completedWord = "embarrass";
        } else if (completedWord.equalsIgnoreCase("facist")) {
            completedWord = "fascist";
        } else if (completedWord.equalsIgnoreCase("febuary")) {
            completedWord = "february";
        } else if (completedWord.equalsIgnoreCase("fivety")) {
            completedWord = "fifty";
        } else if (completedWord.equalsIgnoreCase("fluoroscent")) {
            completedWord = "fluorescent";
        } else if (completedWord.equalsIgnoreCase("flouride")) {
            completedWord = "fluoride";
        } else if (completedWord.equalsIgnoreCase("forteen")) {
            completedWord = "fourteen";
        } else if (completedWord.equalsIgnoreCase("fourty")) {
            completedWord = "forty";
        } else if (completedWord.equalsIgnoreCase("freind")) {
            completedWord = "friend";
        } else if (completedWord.equalsIgnoreCase("geneology")) {
            completedWord = "genealogy";
        } else if (completedWord.equalsIgnoreCase("goverment")) {
            completedWord = "government";
        } else if (completedWord.equalsIgnoreCase("grammer")) {
            completedWord = "grammar";
        } else if (completedWord.equalsIgnoreCase("hampster")) {
            completedWord = "hamster";
        } else if (completedWord.equalsIgnoreCase("harrass")) {
            completedWord = "harass";
        } else if (completedWord.equalsIgnoreCase("hemorage")) {
            completedWord = "haemorrhage";
        } else if (completedWord.equalsIgnoreCase("heros")) {
            completedWord = "heroes";
        } else if (completedWord.equalsIgnoreCase("hight")) {
            completedWord = "height";
        } else if (completedWord.equalsIgnoreCase("hygeine")) {
            completedWord = "hygiene";
        } else if (completedWord.equalsIgnoreCase("hypocracy")) {
            completedWord = "hypocricy";
        } else if (completedWord.equalsIgnoreCase("independance")) {
            completedWord = "independence";
        } else if (completedWord.equalsIgnoreCase("inate")) {
            completedWord = "innate";
        } else if (completedWord.equalsIgnoreCase("innoculate")) {
            completedWord = "inoculate";
        } else if (completedWord.equalsIgnoreCase("intresting")) {
            completedWord = "interesting";
        } else if (completedWord.equalsIgnoreCase("juge")) {
            completedWord = "judge";
        } else if (completedWord.equalsIgnoreCase("knowlege")) {
            completedWord = "knowledge";
        } else if (completedWord.equalsIgnoreCase("libary")) {
            completedWord = "library";
        } else if (completedWord.equalsIgnoreCase("lightening")) {
            completedWord = "lightning";
        } else if (completedWord.equalsIgnoreCase("managable")) {
            completedWord = "manageable";
        } else if (completedWord.equalsIgnoreCase("millenium")) {
            completedWord = "millennium";
        } else if (completedWord.equalsIgnoreCase("mischievious")) {
            completedWord = "mischievous";
        } else if (completedWord.equalsIgnoreCase("mispell")) {
            completedWord = "misspell";
        } else if (completedWord.equalsIgnoreCase("monestary")) {
            completedWord = "monastery";
        } else if (completedWord.equalsIgnoreCase("monkies")) {
            completedWord = "monkeys";
        } else if (completedWord.equalsIgnoreCase("morgage")) {
            completedWord = "mortgage";
        } else if (completedWord.equalsIgnoreCase("mountian")) {
            completedWord = "mountain";
        } else if (completedWord.equalsIgnoreCase("neccessary")) {
            completedWord = "necessary";
        } else if (completedWord.equalsIgnoreCase("neice")) {
            completedWord = "niece";
        } else if (completedWord.equalsIgnoreCase("nickle")) {
            completedWord = "nickel";
        } else if (completedWord.equalsIgnoreCase("nineth")) {
            completedWord = "ninth";
        } else if (completedWord.equalsIgnoreCase("ninty")) {
            completedWord = "ninety";
        } else if (completedWord.equalsIgnoreCase("noone")) {
            completedWord = "no one";
        } else if (completedWord.equalsIgnoreCase("noticable")) {
            completedWord = "noticeable";
        } else if (completedWord.equalsIgnoreCase("occured")) {
            completedWord = "occurred";
        } else if (completedWord.equalsIgnoreCase("ocurence")) {
            completedWord = "occurrence";
        } else if (completedWord.equalsIgnoreCase("oppurtunity")) {
            completedWord = "opportunity";
        } else if (completedWord.equalsIgnoreCase("opthamologist")) {
            completedWord = "ophthalmologist";
        } else if (completedWord.equalsIgnoreCase("paralell")) {
            completedWord = "parallel";
        } else if (completedWord.equalsIgnoreCase("parallell")) {
            completedWord = "parallel";
        } else if (completedWord.equalsIgnoreCase("pasttimee")) {
            completedWord = "pastime";
        } else if (completedWord.equalsIgnoreCase("pavillion")) {
            completedWord = "pavilion";
        } else if (completedWord.equalsIgnoreCase("peice")) {
            completedWord = "piece";
        } else if (completedWord.equalsIgnoreCase("percieve")) {
            completedWord = "perceive";
        } else if (completedWord.equalsIgnoreCase("perserverance")) {
            completedWord = "perseverance";
        } else if (completedWord.equalsIgnoreCase("persue")) {
            completedWord = "pursue";
        } else if (completedWord.equalsIgnoreCase("posesesion")) {
            completedWord = "possession";
        } else if (completedWord.equalsIgnoreCase("potatoe")) {
            completedWord = "potato";
        } else if (completedWord.equalsIgnoreCase("preceeding")) {
            completedWord = "preceding";
        } else if (completedWord.equalsIgnoreCase("pronounciation")) {
            completedWord = "pronunciation";
        } else if (completedWord.equalsIgnoreCase("privelige")) {
            completedWord = "privilege";
        } else if (completedWord.equalsIgnoreCase("publically")) {
            completedWord = "publicly";
        } else if (completedWord.equalsIgnoreCase("quew")) {
            completedWord = "queue";
        } else if (completedWord.equalsIgnoreCase("rasberry")) {
            completedWord = "raspberry";
        } else if (completedWord.equalsIgnoreCase("recieve")) {
            completedWord = "receive";
        } else if (completedWord.equalsIgnoreCase("reccomend")) {
            completedWord = "recommend";
        } else if (completedWord.equalsIgnoreCase("rediculous")) {
            completedWord = "ridiculous";
        } else if (completedWord.equalsIgnoreCase("reguardless")) {
            completedWord = "regardless";
        } else if (completedWord.equalsIgnoreCase("rythm")) {
            completedWord = "rhythm";
        } else if (completedWord.equalsIgnoreCase("shedule")) {
            completedWord = "schedule";
        } else if (completedWord.equalsIgnoreCase("seige")) {
            completedWord = "siege";
        } else if (completedWord.equalsIgnoreCase("sentance")) {
            completedWord = "sentence";
        } else if (completedWord.equalsIgnoreCase("seperate")) {
            completedWord = "separate";
        } else if (completedWord.equalsIgnoreCase("sieze")) {
            completedWord = "seize";
        } else if (completedWord.equalsIgnoreCase("sincerly")) {
            completedWord = "sincerely";
        } else if (completedWord.equalsIgnoreCase("speach")) {
            completedWord = "speech";
        } else if (completedWord.equalsIgnoreCase("stragedy")) {
            completedWord = "strategy";
        } else if (completedWord.equalsIgnoreCase("supercede")) {
            completedWord = "supersede";
        } else if (completedWord.equalsIgnoreCase("suprise")) {
            completedWord = "surprise";
        } else if (completedWord.equalsIgnoreCase("thier")) {
            completedWord = "their";
        } else if (completedWord.equalsIgnoreCase("tomorow")) {
            completedWord = "tomorrow";
        } else if (completedWord.equalsIgnoreCase("tounge")) {
            completedWord = "tongue";
        } else if (completedWord.equalsIgnoreCase("uneform")) {
            completedWord = "uniform";
        } else if (completedWord.equalsIgnoreCase("vaccuum")) {
            completedWord = "vacuum";
        } else if (completedWord.equalsIgnoreCase("vegeterian")) {
            completedWord = "vegetarian";
        } else if (completedWord.equalsIgnoreCase("writen")) {
            completedWord = "written";
        } else if (completedWord.equalsIgnoreCase("writting")) {
            completedWord = "writing";
        } else if (completedWord.equalsIgnoreCase("calender")) {
            completedWord = "calendar";
        } else if (completedWord.equalsIgnoreCase("comming")) {
            completedWord = "coming";
        } else if (completedWord.equalsIgnoreCase("softwares")) {
            completedWord = "software";
        } else if (completedWord.equalsIgnoreCase("thats")) {
            completedWord = "that's";
        } else if (completedWord.equalsIgnoreCase("u")) {
            completedWord = "you";
        }
        return completedWord;
    }
}