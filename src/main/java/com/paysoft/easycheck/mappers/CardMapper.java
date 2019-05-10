package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.CardDTO;
import com.paysoft.easycheck.models.Card;
import com.paysoft.easycheck.models.Customer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {

    public static CardDTO mapTo(Card card) {
        if (card == null) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setID(card.getID());
        cardDTO.setLastFour(card.getLastFour());
        cardDTO.setToken(card.getToken());
        cardDTO.setCustomerID(card.getCustomer().getID());
        cardDTO.setBlocked(card.isBlocked());

        return cardDTO;
    }

    public static Card mapTo(CardDTO cardDTO, Customer customer) {
        if (cardDTO == null) {
            return null;
        }

        Card card = new Card();
        card.setLastFour(cardDTO.getLastFour());
        card.setToken(cardDTO.getToken());
        card.setCustomer(customer);
        card.setBlocked(cardDTO.isBlocked());

        return card;
    }

    public static List<CardDTO> mapTo(List<Card> cards) {
        if (cards.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return cards.stream()
            .map(card -> mapTo(card))
            .filter(cardDTO -> cardDTO != null)
            .collect(Collectors.toList());
    }
}
