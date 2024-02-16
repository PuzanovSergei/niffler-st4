package guru.qa.niffler.page.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMsg implements Msg {
  CATEGORY_MSG("Can not add new category"),
  UPDATE_PROFILE("Error while updating profile");

  private final String msg;

  @Override
  public String getMessage() {
    return msg;
  }
}
