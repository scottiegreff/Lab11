public interface Protocol {
    int PORT = 6969;
    int ADD_ITEM = 1;
    int CHECK_INVENTORY = 2;
    int TAKE_ITEM = 3;

    // TODO int GET_THRESHOLD = 4;
    int SUCCEED = 5;
    int QUIT = 6;
    int INVALID_COMMAND = 7;
}

/*
Client side:					Server side:
----------------------------------------------
ADD_ITEM item_name, quantity        SUCCEED
CHECK_INVENTORY item_name		    SUCCEED
TAKE_ITEM  item_name, quantity      SUCCEED
GET_THRESHOLD ?              SUCCEED
QUIT							    CLOSED
----------------------------------------------
item_name: String
quantity: int
threshold: ?
*/
