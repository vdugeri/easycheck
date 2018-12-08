package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.CardDTO;
import com.paysoft.easycheck.mappers.CardMapper;
import com.paysoft.easycheck.models.Card;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.repositories.CardRepository;
import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.utils.PaginationMetadata;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class CardService {

    @Inject
    private CardRepository cardRepository;

    @Inject
    private UserService userService;

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param limit
     * @param offset
     *
     * @return Paginated list of {@link Card}
     */
    public PaginatedResource<CardDTO> getAll(int limit, int offset) {
        List<Card> cards = cardRepository.findWithLimitAndOffset(limit, offset);

        if (cards.isEmpty()) {
            return new PaginatedResource<>();
        }

        int total = cardRepository.count();
        int pages = (int) Math.ceil(total / limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setPages(pages);
        metadata.setPerPage(limit);
        metadata.setTotal(total);

        PaginatedResource<CardDTO> cardsPaginated = new PaginatedResource<>();
        cardsPaginated.setMeta(metadata);
        cardsPaginated.setData(CardMapper.mapTo(cards));

        return cardsPaginated;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param cardDTO {@link CardDTO}
     * @param customer {@link Customer}
     *
     * @return card
     */
    public CardDTO save(CardDTO cardDTO, Customer customer) {
        return CardMapper.mapTo(cardRepository.create(CardMapper.mapTo(cardDTO, customer)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param userID user id
     *
     * @return list of {@link Card}
     */
    public List<CardDTO> getUserCards(Long userID) {
        Optional<Customer> user = userService.findOne(userID);

        if(user.isPresent()) {
            return CardMapper.mapTo(user.get().getCards());
        }

        return Collections.EMPTY_LIST;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID card id
     */
    public void remove(Long ID) {
        cardRepository.delete(ID);
    }
}
