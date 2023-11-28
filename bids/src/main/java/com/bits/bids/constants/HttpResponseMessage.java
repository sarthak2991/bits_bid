package com.bits.bids.constants;

public class HttpResponseMessage {
  public static final String INCORRECT_PASSWORD = "Incorrect password.";
  public static final String PASSWORD_TOO_SHORT = "Password should have at least #min# characters.";
  public static final String PASSWORD_TOO_LONG =
      "Password should not have more than #max# characters.";
  public static final String INVALID_EMAIL_ADDRESS = "Invalid email address.";
  public static final String INVALID_PHONE_NO = "Invalid phone number.";
  public static final String COUNTRY_CODE_IS_REQUIRED = "Country code is required in phone number.";
  public static final String PHONE_NO_OR_EMAIL_ADDRESS_IS_ALREADY_EXIST =
      "Email Address or phone number is already exist.";
  public static final String AUTHORIZATION_HEADER_IS_MISSING = "Authorization failed.";
  public static final String UNKNOWN_ERROR_MESSAGE = "Something went wrong please try again later.";
  public static final String ACCESS_TOKEN_EXPIRED = "Access token is either expired or invalid.";
  public static final String PHONE_NO_OR_EMAIL_ADDRESS_DOES_NOT_EXIST =
      "Phone number or email is not registered with any user.";
  public static final String PHONE_NO_OR_EMAIL_ADDRESS_IS_MANDATORY =
      "Please enter phone number or email address.";
  public static final String UPDATE_PASSWORD_URL_TOKEN_EXPIRED =
      "Update password url token expire.";
  public static final String NAME_IS_REQUIRED = "Name is required.";
  public static final String DOB_IS_REQUIRED = "Date of Birth is required";
  public static final String NAME_TOO_SHORT = "Name should have at least #min# characters.";
  public static final String NAME_TOO_LONG = "Name should not have more than #max# characters.";
  public static final String NAME_IS_ALREADY_EXIST = "Name is already exist.";
  public static final String INVALID_AUTHORIZATION_HEADER = "Authorization header is invalid.";
  public static final String USER_DOES_NOT = "User does not exist.";
  public static final String PHONE_NO_TOO_SHORT = "Phone number should have at least #min# digits.";
  public static final String PHONE_NO_TOO_LONG =
      "Phone number should not have more than #max# digits.";
  public static final String OAUTH_TOKEN_REQUIRED = "OAuth Token is missing.";
  public static final String TOKEN_EXPIRED_OR_INVALID = "Access token is expired or missing.";
  public static final String EMAIL_ADDRESS_IS_ALREADY_EXIST =
      "You have already signed up using this gmail, Cant use this gmail for signing up with google.";
  public static final String TRY_LOGGING_WITH_GOOGLE = "Try to login with google.";
  public static final String IMAGE_TYPE_NOT_SUPPORTED =
      "Supported images types are png, jpg and jpeg.";
  public static final String IMAGE_IS_REQUIRED = "Please select an image.";
  public static final String BOARD_TITLE_IS_REQUIRED = "Board title is required.";
  public static final String BOARD_TITLE_TOO_SHORT =
      "Board title should have at least #min# characters.";
  public static final String BOARD_TITLE_TOO_LONG =
      "Board title should not have more than #max# characters.";
  public static final String COLLABORATOR_IDS_IS_REQUIRED =
      "Please add at least one collaborators.";
  public static final String BOARD_TITLE_IS_ALREADY_EXIST = "Board #boardName# already exist.";
  public static final String INVALID_USER_IDS = "Users are not registered.";
  public static final String USERNAME_IS_REQUIRED = "Please choose an username.";
  public static final String USERNAME_TOO_LONG =
      "Username should not have more than #max# characters.";
  public static final String USERNAME_TOO_SHORT = "Username should have at least #min# characters.";
  public static final String USERNAME_MIN_ALPHABET_COUNT =
      "Username should have at least #min# alphabets.";
  public static final String USERNAME_IS_INVALID =
      "Username can only have alphabets, numbers, dots and underscore.";
  public static final String INVALID_NAME = "Name can only have alphabets, dot and white space.";
  public static final String TASK_TITLE_IS_ALREADY_EXIST = "Task #taskName# already exist.";
  public static final String USERS_NOT_ASSOCIATED_AS_ASSIGNEE =
      "Some users are not assignees to the task.";
  public static final String TASK_TITLE_IS_REQUIRED = "Task title is required.";
  public static final String TASK_TITLE_TOO_SHORT =
      "Task title should have at least #min# characters.";
  public static final String TASK_TITLE_TOO_LONG =
      "Task title should not have more than #max# characters.";
  public static final String ASSIGNEE_IDS_IS_REQUIRED = "Please add at least one assignees.";
  public static final String TASK_PRIORITY_IS_REQUIRED = "Task priority is required.";
  public static final String TASK_TYPE_IS_REQUIRED = "Task type is required.";
  public static final String TASK_START_TIME_IS_REQUIRED = "Task start time is required.";
  public static final String INVALID_START_TIME = "Start time cannot be before present time.";
  public static final String INVALID_END_TIME = "End time cannot be before start time.";
  public static final String BOARD_NOT_FOUND = "Board not found.";
  public static final String TASK_NOT_FOUND = "Task not found.";
  public static final String INVALID_TASK_ID = "Invalid Task id.";
  public static final String ASSIGNEE_NOT_FOUND = "Assignee #assigneeId# not found.";
  public static final String TASK_STATUS_IS_REQUIRED = "Task status is required";
  public static final String BOARD_ACCESS_DENIED = "Board access denied.";
  public static final String TASK_ACCESS_DENIED = "Task access denied.";
  public static final String ROLE_NOT_FOUND = "Role not found";
  public static final String ADD_COLLABORATOR_LIMIT_EXCEEDED =
      "Can not add more than #max# users at once.";
  public static final String CAN_NOT_ADD_COLLABORATOR = "You can not add other users in the board.";
  public static final String USER_ALREADY_ASSOCIATED_AS_COLLABORATOR =
      "All users are already associated with the board.";
  public static final String CAN_NOT_REMOVE_COLLABORATOR =
      "You can not remove other users from the board.";
  public static final String USERNAME_IS_ALREADY_TAKEN = "Username is not available.";
  public static final String USER_NOT_ASSOCIATED_AS_COLLABORATOR =
      "Some users are not collaborators to the board.";
  public static final String CAN_NOT_UPDATE_TASK = "You can not update this task.";
  public static final String CAN_NOT_UPDATE_TASK_STATUS = "You can not update this task's status.";
  public static final String CAN_NOT_ADD_ASSIGNEE = "You can not add assignee to this task.";
  public static final String CAN_NOT_REMOVE_ASSIGNEE = "You can not remove assignee to this task.";
  public static final String CAN_NOT_DELETE_TASK = "You can not delete this task.";
  public static final String INVALID_TASK_PRIORITY = "Invalid task priority.";
  public static final String INVALID_TASK_STATUS = "Invalid task status.";
  public static final String INVALID_TASK_TYPE = "Invalid task type.";
  public static final String USER_NOT_FOUND = "No user found.";
  public static final String USER_ALREADY_ASSOCIATED_AS_ASSIGNEE =
      "All users are already assigned to this task.";
  public static final String INVALID_ASSIGNEE_IDS = "Some users do not belong to this board.";
  public static final String CREATOR_CANNOT_BE_ADDED_AS_ASSIGNEE =
      "Creator cannot be added as assignee.";
  public static final String RECENT_ACTIVITIES_NOT_FOUND =
      "No recent activity present for the board.";
  public static final String CAN_NOT_SEARCH_BOARD_USERS = "";
  public static final String CAN_NOT_CREATE_TASK = "Can not create task.";
  public static final String INVALID_PAGE_NUMBER = "Page number should ne greater than 0.";
  public static final String NO_START_TIME = "Enter start time first to enter end time.";
  public static final String BOARD_CREATOR_CAN_NOT_BE_ADDED_AS_COLLABORATOR =
      "Board creator can not be added as collaborator.";
  public static final String COLLABORATOR_CAN_NOT_ADD_HIMSELF_AS_COLLABORATOR =
      "Collaborator is already added.";
  public static final String BOARD_CREATOR_CAN_NOT_BE_REMOVED = "You can not remove board creator.";
  public static final String INVALID_BOARD_ID = "Board id is not valid";
  public static final String NULL_FILE = "File uploaded cannot be null.";
  public static final String AWS_CLIENT_ERROR = "Aws Client error.";
  public static final String FILE_SIZE_EXCEEDED = "File size should not be greater than 10mb.";
  public static final String EMAIL_OR_USERNAME_ALREADY_USED = "Email or username or contact no used of others.";
  public static final String NO_SUCH_PRODUCT = "No such product exists.";
  public static final String CATEGORY_ALREADY_EXISTS = "Category already exists.";
  public static final String SEARCH_QUERY_MUST_BE_OF_MIN_CHARS =
      "Search query must be of at least 3 chars";
  public static final String BALANCE_TOP_UP_SUCCESS = "Balance top up success.";
  public static final String YOU_ALREADY_PLACED_BID =
      "You already placed a bid on this product try updating that.";
  public static final String NO_SUCH_CATEGORY = "No such category";
  public static final String PLACED_BID_FIRST = "Place a bid a first";
  public static final String BID_UPDATE_SUCCESS = "Bid update success.";
  public static final String INSUFFICIENT_BALANCE = "insufficient balance";
  public static final String SOMETHING_WRONG = "Something's wrong.";
  public static final String CANT_VIEW_BUYER_INFO = "Cant view buyer info";
  public static final String PRODUCT_ALREADY_EXISTS =
      "You already have this product in your catalog";
  public static final String BID_GREATER_THAN_BASE_PRICE = "Bid should be greater than base price.";
  public static final String CANT_BID_ON_OWN_PRODUCT = "Cant bid on your own product.";
  public static final String MSG_SENT = "message sent.";
  public static final String CHAT_WITH_OTHERS = "Chat with others";
  public static final String BID_NOT_FROZEN = "Bid is not frozen.";
  public static final String PRODUCT_NOT_IN_AVAILABLE_STATE = "Product not in available state.";
  public static final String IMAGE_INVALID = "Image invalid.";
  public static final String IMAGE_FORMAT_NOT_ALLOWED = "only png jpeg and jpg images allowed.";
}
