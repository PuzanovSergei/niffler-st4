package guru.qa.niffler.service;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecificRequestDumperFilterTest {

    String urlPatterns = "helloMock";

    @Test
    void doFilter(@Mock ServletResponse response,
                  @Mock FilterChain chain, @Mock GenericFilter decorate,
                  @Mock HttpServletRequest hRequest) throws ServletException, IOException {
        when(hRequest.getRequestURI()).thenReturn(urlPatterns);
        new SpecificRequestDumperFilter(decorate, urlPatterns).doFilter(hRequest, response, chain);
        verify(decorate, times(1)).doFilter(hRequest, response, chain);
    }

    @Test
    void destroy(@Mock GenericFilter decorate) {
        new SpecificRequestDumperFilter(decorate, urlPatterns).destroy();
        verify(decorate, times(1)).destroy();
    }
}
