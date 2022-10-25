import java.util.*;
import java.lang.Math;

/** ENUMS **/

enum SkillType {
    DAMAGE,
    DAMAGE_HEAL,
    DAMAGE_SELF_STAT,
    DAMAGE_ENEMY_STAT,
    DAMAGE_SELF_STAT_CHANCE,
    DAMAGE_ENEMY_STAT_CHANCE,
    AOE_DAMAGE,
    AOE_DAMAGE_CHANCE,
    SELF_STAT,
    TARGET_STAT,
    DAMAGE_PER_TURN,
    SELF_DAMAGE_FOR_STAT,
    HEAL_SELF,
    HEAL_TEAM,
    MULTI_HITS,
    DOUBLE_HITS,
    CRIT_BOOST,
    GLOBAL_STAT_RESET,
    TAUNT,
    DISABLE,
    REVERSE_HERO_QUEUE,
    MIMIC,
    DEATH_NOTICE,
    DISABLE_NON_DAMAGING_SKILLS,
    DAMAGE_STATUS_CHANCE,
    DAMAGE_SCALE_WITH_ENEMY_STAT
}

enum HeroStat {
    HP,
    ATTACK,
    DEFENSE,
    SPEED
}

enum StatusType {
    CRIT_BOOST,
    DISABLE,
    DAMAGE_PER_TURN,
    SELF_DAMAGE_FOR_STAT,
    TAUNT,
    DEATH_NOTICE,
    POISON
}

enum BattleStatusType {
    REVERSE_HERO_QUEUE,
    DISABLE_NON_DAMAGING_SKILLS
}

/********************************************

  GAME CONSTANTS

  This class contains static fields for the
  sake of anything that is constant (i.e., 
  the list of heroes, etc.)

*********************************************/

class GameConstants {

    static Hero[] heroList = {

        // Bless You: 12% chance to heal ally every turn.
        new Hero(
            "Effelia",
            "Druidess",
            95 * 6,
            95,
            80,
            90,
            0.08F,
            new Skill[] {
                new Skill("Pierce", SkillType.DAMAGE, 90, new int[0], 50, 0.95F, true, false),
                new Skill("Nature's Blessing", SkillType.SELF_STAT, 0, new int[] {1, 3}, 5, 1F, false, false),
                new Skill("Garden of Thorns", SkillType.DAMAGE_PER_TURN, 0, new int[0], 5, 0.8F, true, false), 
                new Skill("Siphon Life", SkillType.DAMAGE_HEAL, 100, new int[0], 3, 0.9F, true, true)
            },
            null
        ),

        // Mysterious Drug: At 25% HP, randomly raises ally team's stat.
        new Hero(
            "Cadeceus",
            "Plague Doctor",
            95 * 6,
            95,
            90,
            80,
            0.08F,
            new Skill[] {
                new Skill("Incision", SkillType.DAMAGE, 90, new int[0], 25, 0.95F, true, false),
                new Skill("Noxious Blast", SkillType.DAMAGE_ENEMY_STAT, 50, new int[] {-6}, 10, 0.85F, true, false),
                new Skill("Death Notice", SkillType.DEATH_NOTICE, 0, new int[0], 5, 0, true, false),
                new Skill("Plague Rain", SkillType.AOE_DAMAGE, 120, new int[] {-10}, 1, 0.9F, false, true) 
            },
            null
        ),
        
        new Hero(
            "Axel",
            "Paladin",
            100 * 6,
            80,
            115,
            65,
            0.05F,
            new Skill[] {
                new Skill("Bash", SkillType.DAMAGE_ENEMY_STAT_CHANCE, 65, new int[] {-2}, 20, 0.9F, true, false),
                new Skill("Fortify", SkillType.SELF_STAT, 0, new int[] {2}, 5, 1F, false, false),
                new Skill("Taunt", SkillType.TAUNT, 0, new int[0], 3, 1F, true, false),
                new Skill("Holy Light", SkillType.HEAL_TEAM, 25, new int[0], 1, 1F, false, true),
            },
            null
        ),

        // Smoke Screen: has % chance of evading atk in 1 turn, has a cooldown of 2 turn.
        new Hero(
            "Medea",
            "Shadow Elf",
            85 * 6,
            110,
            70,
            95,
            0.1F,
            new Skill[] {
                new Skill("Crippling Shot", SkillType.DAMAGE_ENEMY_STAT_CHANCE, 75, new int[] {-1}, 20, 0.95F, true, false),
                new Skill("Bowfaire", SkillType.CRIT_BOOST, 0, new int[0], 5, 1F, false, false),
                new Skill("Disable", SkillType.DISABLE, 0, new int[0], 10, 0.9F, true, false),
                new Skill("Arrow Barrage", SkillType.MULTI_HITS, 40, new int[0], 3, 0.85F, true, true)
            },
            null
        ),
        
        new Hero(
            "Ragnar",
            "Berserker",
            90 * 6,
            110,
            85,
            75,
            0.1F,
            new Skill[] {
                new Skill("Vampiric Blow", SkillType.DAMAGE_HEAL, 80, new int[0], 15, 0.95F, true, false),
                new Skill("Fatal Swing", SkillType.DAMAGE_ENEMY_STAT_CHANCE, 55, new int[] {-2}, 20, 0.9F, true, false),
                new Skill("Ravage", SkillType.MULTI_HITS, 20, new int[0], 10, 0.85F, true, false),
                new Skill("Storm's Rage", SkillType.SELF_DAMAGE_FOR_STAT, 0, new int[] {7}, 3, 1, false, true)
            },
            null
        ),

        // 
        new Hero(
            "Huabbi",
            "Peculiar Djinn",
            115 * 6,
            85,
            85,
            75,
            0.05F,
            new Skill[] {
                new Skill("Dual Orbs", SkillType.DOUBLE_HITS, 45, new int[0], 30, 0.85F, true, false),
                new Skill("Trick Room", SkillType.REVERSE_HERO_QUEUE, 0, new int[0], 5, 1, false, false),
                new Skill("Mimic", SkillType.MIMIC, 0, new int[0], 5, 1F, true, false),
                new Skill("Grand Reset", SkillType.GLOBAL_STAT_RESET, 0, new int[0], 2, 1F, false, true)
            },
            null
        ),

        // Valhalla's Valor: 
        new Hero(
            "Sigurd",
            "Valkyrie",
            105 * 6,
            95,
            90,
            70,
            0.08F,
            new Skill[] {
                new Skill("Heavy Smash", SkillType.DAMAGE_SELF_STAT, 90, new int[] {-3}, 30, 0.85F, true, false),
                new Skill("Wild Roar", SkillType.AOE_DAMAGE_CHANCE, 25, new int[] {1}, 5, 0.9F, false, false),
                new Skill("Arena of Valor", SkillType.DISABLE_NON_DAMAGING_SKILLS, 0, new int[0], 3, 1, false, false),
                new Skill("Glacial Crush", SkillType.DAMAGE_ENEMY_STAT, 125, new int[] {-2}, 3, 0.8F, true, true)
            },
            null
        ),

        // Edge's End: Deals bonus damage on target's with status effect.
        new Hero(
            "Hashashin",
            "Assassin of Erisia",
            80 * 6,
            110,
            70,
            100,
            0.15F,
            new Skill[] {
                new Skill("Poison Dagger", SkillType.DAMAGE_STATUS_CHANCE, 80, new int[0], 10, 0.9F, true, false),
                new Skill("Rain of Blades", SkillType.AOE_DAMAGE, 60, new int[0], 10, 0.95F, false, false),
                new Skill("Killing Intent", SkillType.TARGET_STAT, 0, new int[] {-2, -3}, 5, 0.9F, true, false),
                new Skill("Death Blossom", SkillType.DAMAGE_SCALE_WITH_ENEMY_STAT, 0, new int[0], 1, 1, true, true),
            },
            null
        ),    
    };       
}

/********************************************

  GAME CLASS

  This class is the Game itself. This class
  also helps giving utility methods such as
  getInput(), clamp(), delay(), and
  clearScreen().

*********************************************/

class Game {

    static Scanner scanner = new Scanner(System.in);
    
    public Game() {
        
        Game.clearScreen();
        
        System.out.println(":: " + Game.colorize("Alterblade", "RED") + ": " + Game.colorize("Version 0.1 [BETA]", "YELLOW"));
        System.out.println("\t[1] Battle Mode");
        System.out.println("\t[2] Quit");

    }

    public void start() {

        ArrayList<Hero> team1 = new ArrayList<Hero>();
        ArrayList<Hero> team2 = new ArrayList<Hero>();
        boolean is2v2Battle = false;

        while (true) {
        
            if (getInput() == 1) {
                
                Game.clearScreen();

                System.out.println(":: Choose a Battle Mode!");
                System.out.println("\t[1] PvP Battle");
                System.out.println("\t[2] 2v2 Team Battle");

                if (getInput() == 2) 
                    is2v2Battle = true;

                Game.clearScreen();
                displayCharacterList();
                
                System.out.println("\n");
                Hero heroChoice;
                
                System.out.print(Game.colorize("[Player 1]", "BLUE") + " First Hero");
                heroChoice = new Hero(GameConstants.heroList[getInput() - 1], team1);
                heroChoice.name = Game.colorize(heroChoice.name, "BLUE");
                team1.add(heroChoice);
                
                System.out.print(Game.colorize("[Player 2]", "RED") + " First Hero");
                heroChoice = new Hero(GameConstants.heroList[getInput() - 1], team2);
                heroChoice.name = Game.colorize(heroChoice.name, "RED");
                team2.add(heroChoice);
                
                if (is2v2Battle) {
                    System.out.print(Game.colorize("[Player 1]", "BLUE") + " Second Hero");
                    heroChoice = new Hero(GameConstants.heroList[getInput() - 1], team1);
                    heroChoice.name = Game.colorize(heroChoice.name, "BLUE");
                    team1.add(heroChoice);
                    
                    System.out.print(Game.colorize("[Player 2]", "RED") + " Second Hero");
                    heroChoice = new Hero(GameConstants.heroList[getInput() - 1], team2);
                    heroChoice.name = Game.colorize(heroChoice.name, "RED");
                    team2.add(heroChoice);
                }

                Game.clearScreen();
                Battle battle = new Battle(team1, team2);
                battle.start();
            }   
        }
    }

    public static void displayCharacterList() {
        System.out.println(":: Choose Your Character!");
        for (int i = 0; i < GameConstants.heroList.length; i++) {
            Hero currentHero = GameConstants.heroList[i];
            System.out.println("\n[" + (i + 1) + "] " + currentHero.name + " the " +currentHero.heroClass);
            System.out.println(
                colorize("\tSTATS", "YELLOW") +
                " | " + colorize("HP: ", "YELLOW") + currentHero.baseHp +
                " | " + colorize("ATK: ", "YELLOW") + currentHero.baseAttack +
                " | " + colorize("DEF: ", "YELLOW") + currentHero.baseDefense +
                " | " + colorize("SPE: ", "YELLOW") + currentHero.baseSpeed);
            System.out.print(colorize("\tSKILL", "YELLOW"));
            for (int j = 0; j < currentHero.skillSet.length; j++) {
                System.out.print(" | " + currentHero.skillSet[j].name);
            }
        }
    }

    public static String colorize(String item, String color) {
        String out = item.toString();
        String ANSI_RESET = "\u001B[0m";
        if ( color == "RED" ) {
            return "\u001B[31m" + out + ANSI_RESET;
        } else if (color == "YELLOW") {
            return "\u001B[33m" + out + ANSI_RESET;
        } else if (color == "GREEN") {
            return "\u001B[32m" + out + ANSI_RESET;
        } else if (color == "BLUE") {
            return "\u001B[34m" + out + ANSI_RESET;
        } else if (color == "PORPOL") {
            return "\u001B[35m" + out + ANSI_RESET;
        } else if (color == "BLACK") {
            return "\u001B[30m" + out + ANSI_RESET;
        } else if (color == "RESET") {
            return ANSI_RESET + out + ANSI_RESET;
        }
        return "COLOR_ERROR";
  }

    public static int getInput() {
        System.out.print(": ");
        int out = scanner.nextInt();
        return out;
    }

    public static int rollInt(int min, int max) {
        Random roll = new Random();
        return roll.nextInt((max - min) + 1) + min;
    }
    
    public static String repeat(int count, String with) {
        if ( count < 0 ) count = 0;
        return new String(new char[count]).replace("\0", with);
    }
    
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
    
    public static void delay(float sec) {
        try { Thread.sleep((long)(1000 * sec)); } catch(InterruptedException e) { }
    }
    
    public static void clearScreen() {
        Game.delay(1.5F);
        System.out.println(Game.colorize("\n:: CLEAR SCREEN ::\n", "RED"));
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}

/********************************************

  BATTLE CLASS

  When you initiate a battle, you create a
  Battle object! The Battle class handles the
  interaction, win/lose conditions, and the
  turn-based system between the two players!

*********************************************/
                                                    
class Battle {
    
    ArrayList<Hero> team1 = new ArrayList<Hero>();
    ArrayList<Hero> team2 = new ArrayList<Hero>();
    ArrayList<Hero> heroList = new ArrayList<Hero>();
    ArrayList<Hero> heroQueue = new ArrayList<Hero>();
    ArrayList<Hero> currentTeam;
    ArrayList<Hero> opposingTeam;

    ArrayList<BattleStatus> battleStatuses = new ArrayList<BattleStatus>();

    Hero currentHeroTurn;
    
    boolean is2v2Battle = false;
    boolean isBattleOver = false;
    boolean isNonDamagingMovesAllowed = true; 
    boolean isQueueReversed = false;
    int turnCounter = 1;
  
    public Battle(ArrayList<Hero> team1, ArrayList<Hero> team2) {
        this.team1 = team1;
        this.team2 = team2;
        if (team1.size() > 1)
            is2v2Battle = true;
        heroQueue.addAll(team1);
        heroQueue.addAll(team2);
    }

    public void start() {
        printHeader();
        while (!isBattleOver) {
            Game.delay(2.5F);
            this.proceed();
        }
    }

    public void printHeader() {
        System.out.println("- - - - - - - - -");
        if (is2v2Battle)
            System.out.println("Battle: " + team1.get(0).name + " and " + team1.get(1).name + " vs " + team2.get(0).name + " and " + team2.get(1).name + "!");
        else
            System.out.println("Battle: " + team1.get(0).name + " vs " + team2.get(0).name + "!");
        System.out.println("- - - - - - - - -");
    }
    
    public void proceed() {

        Game.clearScreen();
        
        Collections.sort(this.heroQueue, Hero.heroSpeedComparator);
        if (isQueueReversed)
            Collections.reverse(this.heroQueue);

        Hero targetHero = new Hero();
        
        for (Hero hero: this.heroQueue) {
            
            this.currentHeroTurn = hero;
            
            if (currentHeroTurn.isAlive && !isBattleOver) {
                
                if (getHeroTeam(currentHeroTurn) == team1) {
                    this.currentTeam = team1;
                    this.opposingTeam = team2;
                } else {
                    this.currentTeam = team2;
                    this.opposingTeam = team1;
                }

                int currentPlayerNumber = 1;
                int currentEnemyPlayerNumber = 2;
                String currentPlayerColor = "BLUE";
                String currentEnemyPlayerColor = "RED";
                
                if (currentHeroTurn.team == team2) {
                    currentPlayerNumber = 2;
                    currentEnemyPlayerNumber = 1;
                    currentPlayerColor = "RED";
                    currentEnemyPlayerColor = "BLUE";
                }
                
                System.out.println("- " + Game.colorize("[Player " + currentPlayerNumber + "]", currentPlayerColor) + " - - - - - - - - - - - -");
                Hero.displayHeroStats(currentTeam);
                System.out.println("- " + Game.colorize("[Player " + currentEnemyPlayerNumber + "]", currentEnemyPlayerColor) + " - - - - - - - - - - - -");
                Hero.displayHeroStats(opposingTeam);
                
                System.out.println("\n- - - - - - - - - - - - - - - - - -");
                System.out.println("Turn " + turnCounter + ": " + currentHeroTurn.name + "'s move!");
                Hero.displayHeroSkills(currentHeroTurn);

                System.out.print(Game.colorize("\n[Player " + currentPlayerNumber + "]", currentPlayerColor) + " Skill");
                int skillIndex = Game.getInput() - 1;
                
                while (currentHeroTurn.skillSet[skillIndex].isDisabled) {
                    System.out.println("That skill is disabled!");
                    System.out.print(Game.colorize("\n[Player " + currentPlayerNumber + "]", currentPlayerColor) + " Skill");
                    skillIndex = Game.getInput() - 1;
                }
                
                while (!isNonDamagingMovesAllowed && !currentHeroTurn.skillSet[skillIndex].isDamagingMove) {
                    System.out.println("That skill is disabled!");
                    System.out.print(Game.colorize("\n[Player " + currentPlayerNumber + "]", currentPlayerColor) + " Skill");
                    skillIndex = Game.getInput() - 1;
                }
                
                if (currentHeroTurn.priorityTarget == null) {
                    if (opposingTeam.size() == 2) {
                        if (currentHeroTurn.skillSet[skillIndex].takesTarget) {
                            System.out.print(Game.colorize("[Player " + currentPlayerNumber + "]", currentPlayerColor) + " Target");
                            targetHero = opposingTeam.get(Game.getInput() - 1);
                        }
                    } else {
                        targetHero = opposingTeam.get(0); 
                    }
                } else if (currentHeroTurn.priorityTarget.isAlive) {
                    targetHero = currentHeroTurn.priorityTarget;
                }
                
                currentHeroTurn.team = this.currentTeam;
                targetHero.team = opposingTeam;
                
                System.out.println("");
                currentHeroTurn.doSkill(this, currentHeroTurn.skillSet[skillIndex], targetHero);
                currentHeroTurn.doStatusEffects(targetHero);
                this.doBattleStatus();
                System.out.println("");
                checkWinCondition();

            }
        }
        turnCounter++;
    }
    
    public ArrayList<Hero> getHeroTeam(Hero hero) {
        if (team1.contains(hero))
            return team1;
        else
            return team2;
    }
    
    public void checkWinCondition() {
        if (team1.size() == 0 && team2.size() == 0) {
            Game.delay(1.5F);
            System.out.println("Good battle! It's a draw!");
            isBattleOver = true;
            return;
        }
        
        if (team1.size() == 0) {
            Game.delay(1.5F);
            System.out.println(Game.colorize("[Player 1]", "BLUE") + " has lost! "+ Game.colorize("[Player 2]", "RED") + " claims victory!");
            isBattleOver = true;
        }
        
        if (team2.size() == 0) {
            Game.delay(1.5F);
            System.out.println(Game.colorize("[Player 2]", "RED") + " has lost! "+ Game.colorize("[Player 1]", "BLUE") + " claims victory!");
            isBattleOver = true;
        }
    }

    public void doBattleStatus() {
        for (int i = 0; i < battleStatuses.size(); i++) {
            BattleStatus battleStatus = battleStatuses.get(i);
            if (battleStatus.caster.name == currentHeroTurn.name)
                battleStatus.applyBattleStatus(this);
        }
    }

    public boolean addBattleStatusEffect(BattleStatus battleStatus) {
        // Checks if the status already exists.
        for (BattleStatus status : battleStatuses) {
            if (status.name == battleStatus.name) {
                System.out.println("But it failed... [The battle status still exists.]");
                return false;
            }
        }
        battleStatuses.add(battleStatus);
        return true;
    }
    
    public void removeBattleStatusEffect(BattleStatus battleStatus) {
        battleStatuses.remove(battleStatus);
    }
  
}

/********************************************

  HERO

  These are the Class where the heroes are
  based that the players play and fight with
  the others! The Hero object can cast a Skill
  object per turn and literally implement all
  hero-related stuff such as takeDamage(),
  displayHeroStats(), and so on!

*********************************************/

class Hero {
  
    public String name;
    public String heroClass;
    public Skill[] skillSet = new Skill[4];
    
    public int baseHp;
    public int baseAttack;
    public int baseDefense;
    public int baseSpeed;
    public int currentHp;
    public int currentAttack;
    public int currentDefense;
    public int currentSpeed;
    public float critChance;
    
    public Skill lastSkillUsed;
    public Hero priorityTarget = null;
    public Hero lastHeroAttacker;
    public boolean isAlive = true;
    public ArrayList<Hero> team;
    public ArrayList<Status> statusEffects = new ArrayList<>();

    public Hero(String name, String heroClass, int baseHp, int baseAttack, int baseDefense, int baseSpeed, float critChance, Skill[] skillSet, ArrayList<Hero> team) {
        this.name = name;
        this.heroClass = heroClass;
        this.baseHp = baseHp; currentHp = baseHp;
        this.baseAttack = baseAttack; currentAttack = baseAttack;
        this.baseDefense = baseDefense; currentDefense = baseDefense;
        this.baseSpeed = baseSpeed; currentSpeed = baseSpeed;
        this.critChance = critChance;
        this.team = team;
        for (int i = 0; i < skillSet.length; i++)
            this.skillSet[i] = new Skill(skillSet[i]);
    }

    public Hero() { }
    
    public Hero(Hero hero, ArrayList<Hero> team) {
        this(hero.name, hero.heroClass, hero.baseHp, hero.baseAttack, hero.baseDefense, hero.baseSpeed, hero.critChance, hero.skillSet, team);
    }

    // This handles the comparison of hero to another hero
    // based on their SPEED.
    public static Comparator<Hero> heroSpeedComparator = new Comparator<Hero>() {
        public int compare(Hero hero1, Hero hero2) {
            return hero2.currentSpeed - hero1.currentSpeed;
        }
    };

    // Handles actions when the hero casts a skill.
    public void doSkill(Battle battle, Skill skill, Hero targetHero) {
        System.out.println(name + " does " + skill.name + "!");
        Game.delay(1.5F);
        this.lastSkillUsed = null;
        targetHero.lastHeroAttacker = null;
        skill.activateSkill(battle, this, targetHero);
        if (skill.takesTarget)
            targetHero.lastHeroAttacker = this;
        this.lastSkillUsed = skill;
    }

    // Checks if the hero is still alive (HP > 0).
    public void checkAliveCondition() {
        if (this.currentHp <= 0) {
            this.isAlive = false;
            Game.delay(1.5F);
            System.out.println(this.name + " has fallen in battle!");
            this.name = Game.colorize(this.name, "YELLOW");
            this.team.remove(this);
        }
    }

    // Handles the actions when the hero takes a damage.
    // Refer to this fancy equation to see how the damage is spread by
    // baseDamage, attacker's damage and target's armor:
    // https://www.desmos.com/calculator/s0jjt5ydb2
    public int takeDamage(int baseAmount, int attackerCurrentAttack) {
        if (!this.isAlive) return 0;
        // int staple = ((20 * Math.abs(baseAmount) * ((2 * attackerCurrentAttack) / (currentDefense)))/50) + 5;
        int staple = (int)(50F * (attackerCurrentAttack / (float)this.currentDefense) * Math.sin(baseAmount / 86F)) + 15;
        float bonusMultiplier = 0.9F + (float)(0.2F * Math.random());
        // Min calculated damage would be 0, and max damage would be 300
        int calculatedDamage = (int)Game.clamp(staple * bonusMultiplier, 0, 300F);
        if (calculatedDamage > 0) {
            // Just to avoid negative HPs
            currentHp = (int)Game.clamp(currentHp - calculatedDamage, 0, this.baseHp);
            System.out.println(this.name + " takes " + calculatedDamage + " damage!");
            checkAliveCondition();
        }
        return calculatedDamage;
    }

    // The same as takeDamage() but it deals true damage.
    public void deheal(int amount) {
        currentHp -= (int)Game.clamp(amount, 0, 300);
        this.checkAliveCondition();
    }

    // This is responsible for the actions when healing a hero. 
    public void heal(int amount) {
        int newHp = (int)Game.clamp((float)(currentHp + amount), 0, (float)baseHp);
        if (newHp - currentHp == 0) {
            System.out.println(this.name + "'s HP is already full!");
            return;
        }
        System.out.println(name + " regained " + (newHp - currentHp) + " HP!");
        currentHp = newHp;
    }

    // Displays hero's current stats visually.
    public static void displayHeroStats(ArrayList<Hero> heroList) {
        int index = 1;
        for (Hero hero : heroList) {
            System.out.println("[" + index + "]: " + hero.name);
            System.out.println(
                Game.colorize("HP:  ", "YELLOW") +
                Game.repeat( (int)Math.ceil(20 * (hero.currentHp / (float)hero.baseHp)), "\u001B[42m \u001B[42m\u001B[0m") +
                " | " + hero.currentHp + "/" + hero.baseHp
            );
            System.out.println(
                Game.colorize("ATK: ", "YELLOW") + hero.currentAttack + "/" + hero.baseAttack +
                " | " + Game.colorize("DEF: ", "YELLOW") + hero.currentDefense + "/" + hero.baseDefense +
                " | " + Game.colorize("SPE: ", "YELLOW") + hero.currentSpeed + "/" + hero.baseSpeed +
                " | " + Game.colorize("CRI: ", "YELLOW") + (int)(hero.critChance * 100) +
                "% | " + hero.statusEffects.size());
            index++;
        }
    }

    public static void displayHeroSkills(Hero hero) {
        for (int i = 0; i < hero.skillSet.length; i++) {
            Skill currentSkill = hero.skillSet[i];
            System.out.println("\t[" + (i + 1) + "]" + ": " + currentSkill.name + " | BD: " + currentSkill.baseDamage + " | SP: " + currentSkill.skillPoint);
        }
    }

    // STATS: 1 for ATK, 2 for DEF, 3 for SPE, 4 for twice ATK, 5 for twice DEF, 6 for twice SPE
    public void modifyStat(int index) {
        int base = 1;
        String temp = "rose";
        if (index < 0) {
            base = -1;
            temp = "fell";
        }
        if (Math.abs(index) == 10) index = Game.rollInt(1, 3);
        switch (Math.abs(index)) {
            case 1: {
                System.out.println(this.name + "'s attack " + temp + "!");
                this.currentAttack = (int)Game.clamp(this.currentAttack + (int)Math.floor(0.25F * this.baseAttack) * base, 25, 250);
                break;
            } case 2: {
                System.out.println(this.name + "'s defense " + temp + "!");
                this.currentDefense = (int)Game.clamp(this.currentDefense + (int)Math.floor(0.25F * this.baseDefense) * base, 25, 250);
                break;
            } case 3: {
                System.out.println(this.name + "'s speed " + temp + "!");
                this.currentSpeed = (int)Game.clamp(this.currentSpeed + (int)Math.floor(0.25F * this.baseSpeed) * base, 25, 250);
                break;
            } case 4: {
                System.out.println(this.name + "'s attack sharply " + temp + "!");
                this.currentAttack = (int)Game.clamp(this.currentAttack + (int)Math.floor(0.5F * this.baseAttack) * base, 25, 250);
                break;
            } case 5: {
                System.out.println(this.name + "'s defense sharply " + temp + "!");
                this.currentDefense = (int)Game.clamp(this.currentDefense + (int)Math.floor(0.5F * this.baseDefense) * base, 25, 250);
                break;
            } case 6: {
                System.out.println(this.name + "'s speed sharply " + temp + "!");
                this.currentSpeed = (int)Game.clamp(this.currentSpeed + (int)Math.floor(0.5F * this.baseSpeed) * base, 25, 250);
                break;
            } case 7: {
                System.out.println(this.name + "'s attack drastically " + temp + "!");
                this.currentAttack = (int)Game.clamp(this.currentAttack + (int)Math.floor(0.75F * this.baseAttack) * base, 15, 250);
                break;
            } case 8: {
                System.out.println(this.name + "'s defense drastically " + temp + "!");
                this.currentDefense = (int)Game.clamp(this.currentDefense + (int)Math.floor(0.75F * this.baseDefense) * base, 15, 250);
                break;
            } case 9: {
                System.out.println(this.name + "'s defense drastically " + temp + "!");
                this.currentSpeed = (int)Game.clamp(this.currentSpeed + (int)Math.floor(0.75F * this.baseSpeed) * base, 15, 250);
                break;
            }
        }
    }

    public void resetStat() {
        this.currentAttack = this.baseAttack;
        this.currentDefense = this.baseDefense;
        this.currentSpeed = this.baseSpeed;
    }

    // Updates the status effects of the hero.
    public void doStatusEffects(Hero targetHero) {
        Game.delay(1.5F);
        for (int i = 0; i < statusEffects.size(); i++) {
            statusEffects.get(i).applyStatus();
            Game.delay(2F);
        }
    }

    // Adds a status effect to the Hero. Returns true if it is
    // successful and hence false if adding it fails.
    public boolean addStatusEffect(Status statusEffect) {
        // Checks if the status already exist.
        for (Status status : statusEffects) {
            if (status.name == statusEffect.name) {
                System.out.println("But it failed... [Status still exists.]");
                return false;
            }
        }
        statusEffects.add(0, statusEffect);
        return true;
    }
    
    public void removeStatusEffect(Status statusEffect) {
        // Actually, I just copied this on StackOverflow because
        // removing an status in the ArrayList straightforwardly
        // gives me ConcurrentModificationException for some reason.
        statusEffects.remove(statusEffect);
    }
}

/********************************************

  SKILL CLASS

  A Hero, ideally, can have 4 Skill objects!
  A skill can be casted by doSkill(), and all
  skills are classified into DAMAGE, STAT,
  HEAL. DAMAGE deals damage; STAT raises or
  falls a hero stat; HEAL heals the caster;
  and so on!

*********************************************/

class Skill {

    String name;
    SkillType skillType;
    int baseDamage;
    int[] statEffects;
    int skillPoint;
    int baseDamageMultiplier;
    float accuracy;
    
    public boolean takesTarget = true;
    public boolean isDamagingMove = true;
    public boolean isDisabled = false;
    public boolean isUltimate = false;
  
    public Skill(String name, SkillType skillType, int baseDamage, int[] statEffects, int skillPoint, float accuracy, boolean takesTarget, boolean isUltimate) {
        this.name = Game.colorize(name, "PORPOL");
        this.skillType = skillType;
        this.baseDamage = baseDamage;
        this.statEffects = statEffects;
        this.skillPoint = skillPoint;
        this.accuracy = accuracy;
        this.takesTarget = takesTarget;
        this.isUltimate = isUltimate;
        if (this.baseDamage == 0 || this.skillType == SkillType.HEAL_TEAM || this.skillType == SkillType.HEAL_SELF)
            isDamagingMove = false;
    }

    public Skill(Skill skill) {
        this(skill.name, skill.skillType, skill.baseDamage, skill.statEffects, skill.skillPoint, skill.accuracy, skill.takesTarget, skill.isUltimate);
    }
    
    public boolean rollAccuracy(float accuracy, Hero targetHero) {
        boolean willHit = Math.random() >= (1 - accuracy);
        if (!willHit) {
            if (targetHero != null) {
                System.out.println(targetHero.name + " dodged the attack!");
            } else {
                System.out.println("The attack missed!");
            }
        }
        return willHit;
    }

    public boolean rollCritical(float critChance) {
        boolean willCrit = Math.random() >= (1 - critChance);
        if (willCrit) {
            this.baseDamageMultiplier = 2;
            System.out.println("Critical Hit!");
        }
        return willCrit; 
    }

    private boolean damageHero(Hero attackingHero, Hero targetHero) {
        if (this.rollAccuracy(this.accuracy, targetHero)) {
            this.rollCritical(attackingHero.critChance);
            targetHero.takeDamage(this.baseDamage * this.baseDamageMultiplier, attackingHero.currentAttack);
            return true;
        }
        return false;
    }
    
    private void critBoost(Hero attackingHero, Hero targetHero) {
        Status status = new Status(this, StatusType.CRIT_BOOST, attackingHero, targetHero, 5);
        if (attackingHero.addStatusEffect(status))
            System.out.println(attackingHero.name + "'s crit chance is heightened.");
    }
    
    private void statSkill(Hero targetHero, boolean doesMiss) {
        if (!doesMiss) {
            for (int i = 0; i < statEffects.length; i++) {
                targetHero.modifyStat(statEffects[i]);
                if (statEffects.length > 1)
                    Game.delay(1);
            }
        } else if (this.rollAccuracy(this.accuracy, targetHero)) {
            for (int i = 0; i < statEffects.length; i++) {
                targetHero.modifyStat(statEffects[i]);
                if (statEffects.length > 1)
                    Game.delay(1);
            }
        }
    }

    private void healHero(Hero hero) {
        hero.heal((int)(0.01F * this.baseDamage * hero.baseHp));
    }

    private void multiHit(Hero attackingHero, Hero targetHero, int hitCount) {
        int totalDamage = 0;
        for (int i = 1; i <= hitCount; i++) {
            if (targetHero.isAlive && this.rollAccuracy(this.accuracy, null)) {
                this.baseDamageMultiplier = 1;
                System.out.println(i + " hit!");
                this.rollCritical(attackingHero.critChance);
                totalDamage += targetHero.takeDamage(baseDamage * this.baseDamageMultiplier, attackingHero.currentAttack);
            }
            Game.delay(1);
        }
        if (totalDamage > 0) {
            System.out.println("A total of " + totalDamage + " damage!");
        }
    }
    
    public void activateSkill(Battle battle, Hero attackingHero, Hero targetHero) {
        if (skillPoint <= 0) {
            System.out.println("But it failed... [0 Skill Point]");
            return;
        }
        this.baseDamageMultiplier = 1;
        
        switch (this.skillType) {
              
            case DAMAGE: {

                attackingHero.critChance += 0.1F;
                damageHero(attackingHero, targetHero);
                attackingHero.critChance -= 0.1F;
                break;
                
            } case DAMAGE_HEAL: {
                
                if (this.rollAccuracy(this.accuracy, targetHero)) {
                    int damage = targetHero.takeDamage(this.baseDamage * this.baseDamageMultiplier, attackingHero.currentAttack);
                    Game.delay(1.5F);
                    attackingHero.heal((int)(0.5F * damage));
                }
                break;
                
            } case DAMAGE_SELF_STAT: {

                if (this.damageHero(attackingHero, targetHero)) {
                    Game.delay(1.5F);
                    statSkill(attackingHero, false);
                }
                break;
                
            } case DAMAGE_ENEMY_STAT: {
                
                if (this.damageHero(attackingHero, targetHero)) {
                    Game.delay(1.5F);
                    statSkill(targetHero, false);
                }
                break;
                
            } case DAMAGE_SELF_STAT_CHANCE: {

                if (this.damageHero(attackingHero, targetHero)) {
                    Game.delay(1.5F);
                    if (Game.rollInt(1, 2) == 1)
                        statSkill(attackingHero, false);
                }
                break;
                
            } case DAMAGE_ENEMY_STAT_CHANCE: {
                
                if (this.damageHero(attackingHero, targetHero)) {
                    Game.delay(1.5F);
                    if (Game.rollInt(1, 2) == 1)
                        statSkill(targetHero, false);
                }
                break;
                
            } case AOE_DAMAGE: {

                if (this.statEffects.length == 0) {
                    for (int i = 0; i < battle.opposingTeam.size(); i++) {
                        Hero hero = battle.opposingTeam.get(i);
                        this.damageHero(attackingHero, hero);
                        Game.delay(1F);
                    }
                } else if (this.statEffects[0] < 0) {
                    for (int i = 0; i < battle.opposingTeam.size(); i++) {
                        Hero hero = battle.opposingTeam.get(i);
                        if (this.damageHero(attackingHero, hero)) {
                            Game.delay(1);
                            statSkill(hero, false);
                        }
                        Game.delay(1);
                    }
                } else if (statEffects[0] > 0) {
                    for (int i = 0; i < battle.opposingTeam.size(); i++) {
                        Hero hero = battle.opposingTeam.get(i);
                        this.damageHero(attackingHero, hero);
                        Game.delay(1);
                    }
                    statSkill(attackingHero, false);
                }
                break;
                
            } case AOE_DAMAGE_CHANCE: {

                if (this.statEffects[0] < 0) {
                    for (int i = 0; i < battle.opposingTeam.size(); i++) {
                        Hero hero = battle.opposingTeam.get(i);
                        if (this.damageHero(attackingHero, hero)) {
                            Game.delay(1);
                            if (Game.rollInt(1, 2) == 2)
                                statSkill(hero, false);
                        }
                        Game.delay(1);
                    }
                } else if (statEffects[0] > 0) {
                    for (int i = 0; i < battle.opposingTeam.size(); i++) {
                        Hero hero = battle.opposingTeam.get(i);
                        this.damageHero(attackingHero, hero);
                        Game.delay(1);
                    }
                    if (Game.rollInt(1, 2) == 2)
                        statSkill(attackingHero, false);
                }
                break;
                
            } case SELF_STAT: {
                
                statSkill(attackingHero, false);
                break;
                
            } case TARGET_STAT: {

                statSkill(targetHero, true);
                break;
                
            } case DAMAGE_PER_TURN: {

                if (this.rollAccuracy(this.accuracy, targetHero)) {
                    Status status = new Status(this, StatusType.DAMAGE_PER_TURN, attackingHero, targetHero, Game.rollInt(3, 5));
                    if (targetHero.addStatusEffect(status)) {
                        System.out.println(attackingHero.name + " summons a cluster of grasping thorns around " + targetHero.name + "!");
                        Game.delay(1F);
                    }
                }
                break;
                
            } case SELF_DAMAGE_FOR_STAT: {

                Status status = new Status(this, StatusType.SELF_DAMAGE_FOR_STAT, attackingHero, targetHero, 5);
                if (attackingHero.addStatusEffect(status)) {
                    System.out.println(attackingHero.name + " ignites a thundering rage!");
                    Game.delay(1.5F);
                    statSkill(attackingHero, false);
                }
                break;
                
            } case HEAL_SELF: {
                
                healHero(attackingHero);
                break;
                
            } case HEAL_TEAM: {

                for (int i = 0; i < battle.currentTeam.size(); i++) {
                    Hero hero = battle.currentTeam.get(i);
                    healHero(hero);
                    Game.delay(1);
                }
                break;
                
            } case MULTI_HITS: {
              
                multiHit(attackingHero, targetHero, Game.rollInt(2, 5));
                break;
                
            } case DOUBLE_HITS: {

                multiHit(attackingHero, targetHero, 2);
                break;
                
            } case CRIT_BOOST: {
                
                critBoost(attackingHero, targetHero);
                break;
                
            } case TAUNT: {

                Status status = new Status(this, StatusType.TAUNT, attackingHero, targetHero, 1);
                if (targetHero.addStatusEffect(status)) {
                    targetHero.priorityTarget = attackingHero;
                    System.out.println(targetHero.name + " fell on " + attackingHero.name + "'s taunt!");
                }
                break;

            } case DISABLE: {

                Skill skill = targetHero.lastSkillUsed;
                Status status = new Status(this, StatusType.DISABLE, attackingHero, targetHero, Game.rollInt(2, 5));
                if (skill == null || skill.skillPoint == 0) {
                    System.out.println("But it failed... [maybe 0 SPs or the target didn't attacked yet?]");
                } else if (targetHero.addStatusEffect(status)) {
                    status.targetSkill(skill);
                    skill.isDisabled = true;
                    System.out.println(targetHero.name + "'s " + skill.name + " has been disabled!");
                }
                break;
                
            } case MIMIC: {

                Skill targetLastSkill = targetHero.lastSkillUsed;
                if (targetLastSkill == null || targetLastSkill.isUltimate || targetLastSkill.skillType == SkillType.MIMIC)
                    System.out.println("But it failed...");
                else {
                    Skill skillToMimic = new Skill(targetHero.lastSkillUsed);
                    skillToMimic.skillPoint++;
                    attackingHero.doSkill(battle, skillToMimic, targetHero);
                }
                break;
                
            } case REVERSE_HERO_QUEUE: {

                BattleStatus battleStatus = new BattleStatus(this, BattleStatusType.REVERSE_HERO_QUEUE, attackingHero, 5);
                if (battle.addBattleStatusEffect(battleStatus))
                    System.out.println(attackingHero.name + " distorting and shuffling the dimension!");
                break;
                
            } case GLOBAL_STAT_RESET: {

                System.out.println("All stat changes has been reset!");
                for (Hero hero : battle.heroQueue)
                    hero.resetStat();
                break;
                
            } case DEATH_NOTICE: {

                Status status = new Status(this, StatusType.DEATH_NOTICE, attackingHero, targetHero, 3);
                if (targetHero.addStatusEffect(status))
                    System.out.println(attackingHero.name + " puts " + targetHero.name + " on a death notice!");
                break;
                
            } case DISABLE_NON_DAMAGING_SKILLS: {

                BattleStatus battleStatus = new BattleStatus(this, BattleStatusType.DISABLE_NON_DAMAGING_SKILLS, attackingHero, 2);
                if (battle.addBattleStatusEffect(battleStatus)) {
                    battle.isNonDamagingMovesAllowed = false;
                    System.out.println(attackingHero.name + " summons an arena of valor!");
                    Game.delay(1);
                    System.out.println("Any non-damaging move is disabled in the arena!");
                }
                break;
                
            } case DAMAGE_STATUS_CHANCE: {

                Status status = new Status(this, StatusType.POISON, attackingHero, targetHero, 5);
                if (damageHero(attackingHero, targetHero)) {
                    if (Game.rollInt(1, 2) == 2) {
                        if (targetHero.addStatusEffect(status)) {
                            Game.delay(1);
                            System.out.println(targetHero.name + " has been poisoned!");
                        }
                    }
                }
                break;
                
            } case DAMAGE_SCALE_WITH_ENEMY_STAT: {

                if (this.rollAccuracy(this.accuracy, targetHero)) {
                    int raw = (int)Game.clamp((targetHero.currentAttack + targetHero.currentDefense) / 1.5F, 0, 300F);
                    int damage = (int)(((targetHero.baseHp * 0.1F) + raw) * (0.9F + (Game.rollInt(0, 20) * 0.01F)));
                    targetHero.deheal(damage);
                    System.out.println("The blood splatters!");
                    Game.delay(1);
                    System.out.println(targetHero.name + " takes " + damage + " damage!");
                }
                break;
                
            }
                
       }
        this.skillPoint--;
    }
}

/********************************************

  STATUS

  A Status is the effect of a Skill that
  lasts more than one turn. Status objects
  are stoed within the Hero object. Examples
  of Status would be Poison, Crit Boost, etc.

*********************************************/

class Status {

    public String name;
    public int turnDuration;
    public int remainingDuration;
    public StatusType statusType;
    public Hero caster;
    public Hero targetHero;
    private Skill skill;
    private Skill targetSkill;
    
    public Status(Skill skill, StatusType statusType, Hero caster, Hero targetHero, int turnDuration) {
        this.skill = skill;
        this.name = skill.name;
        this.caster = caster;
        this.targetHero = targetHero;
        this.statusType = statusType;
        this.turnDuration = turnDuration;
        this.remainingDuration = turnDuration;
    }

    public void targetSkill(Skill skill) {
        this.targetSkill = skill;
    }
  
    public void applyStatus() {
        if (targetHero.isAlive && remainingDuration >= 0) {
            switch ( statusType ) {
                case CRIT_BOOST: {
    
                    if (remainingDuration == 0) {
                        caster.critChance -= 0.2F;
                        caster.removeStatusEffect(this);
                        System.out.println(caster.name + "'s crit boost has petered out!");
                        return;
                    } else if (remainingDuration == turnDuration) {
                        caster.critChance += 0.2F;    
                    }    
                    break;
                  
                } case DAMAGE_PER_TURN: {
                    
                    if (remainingDuration == 0) {
                        System.out.println(targetHero.name + " has unbinded from the thorns.");
                        targetHero.removeStatusEffect(this);
                    } else {
                        int damage = (int)(0.10F * targetHero.baseHp);
                        System.out.println(targetHero.name + " is hurt by the grasping thorns! R: " + remainingDuration + " turns!");
                        targetHero.deheal(damage);
                    }
                    break;
                    
                } case POISON: {

                    if (remainingDuration == 0) {
                        System.out.println(targetHero.name + " has recovered from the poison!");
                        targetHero.removeStatusEffect(this);
                    } else {
                        int damage = (int)(0.10F * targetHero.baseHp);
                        System.out.println(targetHero.name + " is hurt because of poison!");
                        targetHero.deheal(damage);
                    }
                    break;

                } case SELF_DAMAGE_FOR_STAT: {
    
                    if (remainingDuration == 0) {
                        System.out.println(caster.name + "'s rage has subsided.");
                        caster.modifyStat(-1);
                        caster.removeStatusEffect(this);
                    } else {
                        System.out.println(caster.name + " is hurt by his overwhelming rage! R: " + remainingDuration + " turns!");
                        int damage = (int)(0.10F * caster.baseHp);
                        caster.deheal(damage);
                    }
                    break;
                    
                } case TAUNT: {
                    
                    if (remainingDuration == 0) {
                        targetHero.removeStatusEffect(this);
                        targetHero.priorityTarget = null;
                        System.out.println(targetHero.name + "'s taunt wore off!");
                    }
                    break;
                    
                } case DISABLE: {
    
                    if (remainingDuration == 0) {
                        targetHero.removeStatusEffect(this);
                        targetSkill.isDisabled = false;
                        System.out.println("The disable to " + targetSkill.name + " wore off!");
                    }
                    break;
                    
                } case DEATH_NOTICE: {

                    if (remainingDuration == 0) {
                        System.out.println("The death notice on " + targetHero.name + " vanished!");
                        targetHero.removeStatusEffect(this);
                    } else {
                        if (targetHero.currentHp < (int)(targetHero.baseHp * 0.25F)) {
                            System.out.println(targetHero.name + " was caught by the death notice!");
                            targetHero.deheal(300);
                        }
                    }
                    break;
                    
                }
            }
        }
        remainingDuration--;
    }
}

/********************************************

  BATTLE STATUS

  A BattleStatus is the effect of a Skill that
  lasts more than one turn. Status objects
  are stored within the Battle object. Examples
  of Status would be Trick Room, Battle Call,
  etc.

*********************************************/

class BattleStatus {

    public String name;
    Skill skill;
    BattleStatusType type;
    Hero caster;
    int turnDuration;
    int remainingDuration;

    public BattleStatus(Skill skill, BattleStatusType type, Hero caster, int turnDuration) {
        this.skill = skill;
        this.name = skill.name;
        this.type = type;
        this.caster = caster;
        this.turnDuration = turnDuration;
        this.remainingDuration = turnDuration;
    }
    
    public void applyBattleStatus(Battle battle) {
        if (remainingDuration >= 0) {
            switch (this.type) {
                case REVERSE_HERO_QUEUE: {
                    if (remainingDuration == 0) {
                        System.out.println("The dimension turned back normal.");
                        battle.isQueueReversed = false;
                        battle.removeBattleStatusEffect(this);
                    } else {
                        battle.isQueueReversed = true;
                    }
                    break;
                } case DISABLE_NON_DAMAGING_SKILLS: {
                    if (remainingDuration == 0) {
                        battle.isNonDamagingMovesAllowed = true;
                        System.out.println("The arena of valor vanished!");
                        battle.removeBattleStatusEffect(this);
                    } else {
                        battle.isNonDamagingMovesAllowed = false;
                    }
                    break;
                }
            }
        }
        remainingDuration--;
    }
    
}

/********************************************

  MAIN

  The entry point of the program. This will
  create an instence of a Game and will
  start it.

*********************************************/

class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}