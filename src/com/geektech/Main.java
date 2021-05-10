package com.geektech;

import java.util.Random;

public class Main {

    public static String[] heroesNames = {"Liu Kang", "Jax", "Kung Lao", "Aibolit", "Golem", "Lucky", "Bersercer", "Thor"};
    public static int[] heroesHealth = {270, 280, 250, 250, 500, 300, 320, 290};
    public static int[] heroesDamage = {20, 15, 25, 0, 5, 15, 30, 20};

    public static String bossName = "Shao Kahn";
    public static int bossHealth = 1000;
    public static int bossDamageOriginal = 50;
    public static int bossDamage = bossDamageOriginal;
    public static int roundNumber = 0;
    public static String superDamageHero = "";

    public static void main(String[] args) {
        // write your code here

        printStatistics();
        System.out.println("_____Mortal Kombat started____");

        while (!isGameFinished()) {
            round();
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " +
                    "Mortal Kombat finished ");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName + " Won!!!" +
                    "Mortal Kombat finished");
        }
        return allHeroesDead;
    }

    public static void round() {
        roundNumber++;
        System.out.println("______Round " + roundNumber + "______");
        bossDamage();
        superDamageHero = getHeroForDamageBossDefence();
        heroesDamage();
        aibolit();
        golem();
        lucky();
        berserk();
        thor();
        printStatistics();
    }

    public static void bossDamage() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void heroesDamage() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superDamageHero == heroesNames[i]) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Super damage hero = " +
                            superDamageHero + " " +
                            (heroesDamage[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static boolean aibolit() {
        boolean isHeal = false;
        Random random = new Random();
        int num = random.nextInt(30);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0 && heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                heroesHealth[i] = heroesHealth[i] + num;
                System.out.println("Aibolit heal " + heroesNames[i] + " + " + num + "HP");
                isHeal = true;
                break;
            } else
                System.out.println("Aibolit cant heal");
            break;
        }
        return isHeal;
    }

    public static boolean golem() {
        boolean golemHelp = false;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0 && heroesHealth[i] > 0) {
                heroesHealth[4] = heroesHealth[4] - (bossDamage / 5);
                heroesHealth[i] = heroesHealth[i] + (bossDamage / 5);
                golemHelp = true;
            }
        }
        return golemHelp;
    }

    public static boolean lucky() {
        Random random = new Random();
        boolean chance = random.nextBoolean();
        if (chance && heroesHealth[5] > 0) {
            heroesHealth[5] = heroesHealth[5] + bossDamage;
            System.out.println("Lucky dodged!");
        } else
            System.out.println("Lucky cant dodge");
        return chance;
    }

    public static void berserk() {
        Random random = new Random();
        int num = random.nextInt(2);
        if (num == 0) {
            System.out.println("BERSERKER Cant block");
        }
        if (num == 1 && heroesHealth[6] > 0) {
            System.out.println("BERSERKER BLOCKED");
            bossHealth = bossHealth - (heroesDamage[6] + (bossDamage / 5));
            heroesHealth[6] = heroesHealth[6] - ((bossDamage / 5) * 4);
        }
    }

    public static boolean thor() {
        Random random = new Random();
        boolean answer = random.nextBoolean();
        if (answer && heroesHealth[7] > 0 && bossHealth >0) {
            bossDamage = 0;
            System.out.println("Thor deafen Shaokahn");
        } else if (!answer) {
            bossDamage = bossDamageOriginal;
            System.out.println("Thor cant deafen Shaokahn");
        }
        return answer;
    }

    public static String getHeroForDamageBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }

    public static void printStatistics() {
        System.out.println("\n" + bossName + " = health " + bossHealth +
                " damage [" + bossDamage + "]\n");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " = health " +
                    heroesHealth[i] + " damage [" +
                    heroesDamage[i] + "]");
        }
    }
}
