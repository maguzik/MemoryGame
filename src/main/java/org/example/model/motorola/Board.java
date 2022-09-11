package org.example.model.motorola;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    public static final int ROW_SIZE = 2;
    private Set<Card> cards = new HashSet<>();
    private List<String> allowedLocation = new ArrayList<>();
    private int userUsedChance;
    private int userChanceToUse;

    public Board(DifficultLevel difficultLevel) {


        if (difficultLevel.getDescription() == DifficultLevel.Description.EASY) {
            generateCards(4);
            userChanceToUse = difficultLevel.getGuessChances();
        } else {
            generateCards(8);
            userChanceToUse = difficultLevel.getGuessChances();
        }
        fillCardsWithValue();
        System.out.println(cards.stream().collect(Collectors.toCollection(() -> new TreeSet<>(new ComparatorToLocation()))));

    }

    private void fillCardsWithValue() {
        Random random = new Random();
        List<Card> cardsCopy = new ArrayList<>(cards);
        Collections.shuffle(cardsCopy);
        while (!cardsCopy.isEmpty()) {
            CardValue cardValue;
            do {
                cardValue = CardValue.findByIndex(random.nextInt(cards.size() / 2));

            }
            while (cardValue == null || cardValue.isUsed());
            cardValue.markAsUsed();

            CardValue finalCardValue = cardValue;
            cards.stream().filter(card -> card.getLocation().equals(cardsCopy.get(0).getLocation())
                            || card.getLocation().equals(cardsCopy.get(1).getLocation()))
                    .forEach(card -> card.setValue(finalCardValue));


            cardsCopy.remove(0);
            cardsCopy.remove(0);

        }

    }

    public List<String> getAllowedLocation() {
        return Collections.unmodifiableList(allowedLocation);
    }

    private void generateCards(int columnSize) {

        for (int n = 1; n <= ROW_SIZE; n++) {
            for (int m = 1; m <= columnSize; m++) {
                Location location = new Location(n, m);
                allowedLocation.add(location.getUserFriendlyName());
                cards.add(new Card(location));
            }
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void choseCardByLocation(String userInput) {
        Set<Card> chosenCards = cards.stream().filter(Card::isChosen).collect(Collectors.toSet());
        Card cardFound = cards.stream()
                .filter(card -> card.getLocation().getUserFriendlyName().equals(userInput))
                .findFirst()
                .orElseThrow();
        cardFound.choose();


        if (chosenCards.size() == 2) {
            long uniqueCardsCounter = chosenCards.stream().map(card -> card.getValue().getName()).distinct().count();
            if (uniqueCardsCounter == 2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userUsedChance++;
                chosenCards.forEach(Card::hide);
            } else if (uniqueCardsCounter == 1) {
                chosenCards.forEach(card -> {
                    card.markAsFound();
                    card.hide();

                });

            }
        }


    }

    public boolean isGameOver() {
        System.out.println(userUsedChance);
        return userUsedChance >= userChanceToUse || cards.stream().allMatch(Card::isFound);
    }
}
