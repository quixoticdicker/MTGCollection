package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardParseTest {

    @Test
    void test()
    {
        Card testCard = new Card("  {\"object\":\"card\",\"id\":\"86bf43b1-8d4e-4759-bb2d-0b2e03ba7012\",\"oracle_id\":\"0004ebd0-dfd6-4276-b4a6-de0003e94237\",\"multiverse_ids\":[15862],\"mtgo_id\":15870,\"mtgo_foil_id\":15871,\"tcgplayer_id\":3094,\"name\":\"Static Orb\",\"lang\":\"en\",\"released_at\":\"2001-04-11\",\"uri\":\"https://api.scryfall.com/cards/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012\",\"scryfall_uri\":\"https://scryfall.com/card/7ed/319/static-orb?utm_source=api\",\"layout\":\"normal\",\"highres_image\":true,\"image_uris\":{\"small\":\"https://img.scryfall.com/cards/small/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171\",\"normal\":\"https://img.scryfall.com/cards/normal/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171\",\"large\":\"https://img.scryfall.com/cards/large/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171\",\"png\":\"https://img.scryfall.com/cards/png/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.png?1562242171\",\"art_crop\":\"https://img.scryfall.com/cards/art_crop/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171\",\"border_crop\":\"https://img.scryfall.com/cards/border_crop/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171\"},\"mana_cost\":\"{3}\",\"cmc\":3.0,\"type_line\":\"Artifact\",\"oracle_text\":\"As long as Static Orb is untapped, players can't untap more than two permanents during their untap steps.\",\"colors\":[\"U\",\"G\"],\"color_identity\":[\"R\",\"W\"],\"legalities\":{\"standard\":\"not_legal\",\"future\":\"not_legal\",\"historic\":\"not_legal\",\"pioneer\":\"not_legal\",\"modern\":\"not_legal\",\"legacy\":\"legal\",\"pauper\":\"not_legal\",\"vintage\":\"legal\",\"penny\":\"not_legal\",\"commander\":\"legal\",\"brawl\":\"not_legal\",\"duel\":\"legal\",\"oldschool\":\"not_legal\"},\"games\":[\"mtgo\",\"paper\"],\"reserved\":false,\"foil\":true,\"nonfoil\":true,\"oversized\":false,\"promo\":false,\"reprint\":true,\"variation\":false,\"set\":\"7ed\",\"set_name\":\"Seventh Edition\",\"set_type\":\"core\",\"set_uri\":\"https://api.scryfall.com/sets/230f38aa-9511-4db8-a3aa-aeddbc3f7bb9\",\"set_search_uri\":\"https://api.scryfall.com/cards/search?order=set\u0026q=e%3A7ed\u0026unique=prints\",\"scryfall_set_uri\":\"https://scryfall.com/sets/7ed?utm_source=api\",\"rulings_uri\":\"https://api.scryfall.com/cards/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012/rulings\",\"prints_search_uri\":\"https://api.scryfall.com/cards/search?order=released\u0026q=oracleid%3A0004ebd0-dfd6-4276-b4a6-de0003e94237\u0026unique=prints\",\"collector_number\":\"319\",\"digital\":false,\"rarity\":\"rare\",\"flavor_text\":\"The warriors fought against the paralyzing waves until even their thoughts froze in place.\",\"card_back_id\":\"0aeebaf5-8c7d-4636-9e82-8c27447861f7\",\"artist\":\"Terese Nielsen\",\"artist_ids\":[\"eb55171c-2342-45f4-a503-2d5a75baf752\"],\"illustration_id\":\"6f8b3b2c-252f-4f95-b621-712c82be38b5\",\"border_color\":\"white\",\"frame\":\"1997\",\"full_art\":false,\"textless\":false,\"booster\":true,\"story_spotlight\":false,\"edhrec_rank\":1715,\"related_uris\":{\"gatherer\":\"https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=15862\",\"tcgplayer_decks\":\"https://decks.tcgplayer.com/magic/deck/search?contains=Static+Orb\u0026page=1\u0026partner=Scryfall\u0026utm_campaign=affiliate\u0026utm_medium=scryfall\u0026utm_source=scryfall\",\"edhrec\":\"https://edhrec.com/route/?cc=Static+Orb\",\"mtgtop8\":\"https://mtgtop8.com/search?MD_check=1\u0026SB_check=1\u0026cards=Static+Orb\"}},");
        assertTrue(testCard.name.equals("Static Orb"));
        assertTrue(testCard.imgThumbnailURL.equals("https://img.scryfall.com/cards/small/front/8/6/86bf43b1-8d4e-4759-bb2d-0b2e03ba7012.jpg?1562242171"));
        assertTrue(testCard.color[Card.eColor.BLUE.value]);
        assertTrue(testCard.color[Card.eColor.GREEN.value]);
        assertFalse(testCard.color[Card.eColor.BLACK.value]);
        assertFalse(testCard.color[Card.eColor.RED.value]);
        assertFalse(testCard.color[Card.eColor.WHITE.value]);

        assertTrue( testCard.color_identity[Card.eColor.RED.value]);
        assertTrue( testCard.color_identity[Card.eColor.WHITE.value]);
        assertFalse(testCard.color_identity[Card.eColor.BLACK.value]);
        assertFalse(testCard.color_identity[Card.eColor.BLUE.value]);
        assertFalse(testCard.color_identity[Card.eColor.GREEN.value]);
    }

}
