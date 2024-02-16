package guru.qa.niffler.page.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SuccessMsg implements Msg {
  PROFILE_MSG("Profile successfully updated"),
  NEW_CATEGORY_ADD("New category added");

  private final String msg;

  @Override
  public String getMessage() {
    return msg;
  }
}
