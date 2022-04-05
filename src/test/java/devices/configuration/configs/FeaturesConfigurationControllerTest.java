package devices.configuration.configs;

import devices.configuration.JsonAssert;
import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static devices.configuration.JsonAssert.json;
import static devices.configuration.configs.FeaturesConfigurationFixture.entity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeaturesConfigurationController.class)
@AutoConfigureMockMvc(addFilters = false)
class FeaturesConfigurationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FeaturesConfigurationRepository repository;
    @SpyBean
    private IntervalRulesDocumentRepository intervalRules;

    @Test
    public void existingCurrentRulesOfIntervalRulesConfig() throws Exception {
        // given
        String configName = "IntervalRules";
        when(repository.findByName(configName)).thenReturn(
                Optional.of(entity(configName, json(IntervalRulesFixture.currentRules())))
        );

        // when
        String result = mockMvc.perform(get("/configs/{configName}", configName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        JsonAssert.assertThat(result)
                .isExactlyLike(IntervalRulesFixture.currentRules());
    }

    @Test
    public void notExistingConfigName() throws Exception {
        // given
        String configName = "Missing";
        when(repository.findByName(configName)).thenReturn(
                Optional.empty()
        );

        // expected
        mockMvc.perform(get("/configs/{config}", configName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putNewConfig() throws Exception {
        String configName = "OtherConfig";
        when(repository.findByName(configName)).thenReturn(
                Optional.empty()
        );
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        String result = mockMvc.perform(put("/configs/{configName}", configName)
                        .content(json(IntervalRulesFixture.currentRules()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        JsonAssert.assertThat(result)
                .isExactlyLike(IntervalRulesFixture.currentRules());
    }

    @Test
    public void updateConfig() throws Exception {
        String configName = "OtherConfig";
        when(repository.findByName(configName)).thenReturn(
                Optional.of(entity(configName, json(IntervalRules.defaultRules())))
        );
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        String result = mockMvc.perform(put("/configs/{configName}", configName)
                        .content(json(IntervalRulesFixture.currentRules()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        JsonAssert.assertThat(result)
                .isExactlyLike(IntervalRulesFixture.currentRules());
    }

    @Test
    public void putBrokenIntervalRules() throws Exception {
        String configName = IntervalRulesDocumentRepository.CONFIG_NAME;
        when(repository.findByName(configName)).thenReturn(
                Optional.empty()
        );
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        mockMvc.perform(put("/configs/{configName}", configName)
                        .content(IntervalRulesFixture.brokenRules())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

