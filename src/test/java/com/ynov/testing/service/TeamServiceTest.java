//package com.ynov.testing.service;
//
//import com.ynov.testing.model.Team;
//import com.ynov.testing.repository.TeamRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class TeamServiceTest {
//
//    @InjectMocks
//    private TeamService teamService;
//
//    @Mock
//    private TeamRepository teamRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private Team createSampleTeam(Long id) {
//        Team team = new Team();
//        team.setId(id);
//        team.setName("Team Alpha");
//        team.setRegion("Europe");
//        team.setFoundedDate(LocalDate.of(2015, 5, 10).atStartOfDay());
//        team.setActive(true);
//        return team;
//    }
//
//    @Test
//    void testGetAllTeams() {
//        List<Team> mockTeams = Arrays.asList(createSampleTeam(1L), createSampleTeam(2L));
//        when(teamRepository.findAll()).thenReturn(mockTeams);
//
//        List<Team> result = teamService.getAllTeams();
//        assertEquals(2, result.size());
//        verify(teamRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetTeamById_validId() {
//        Team mockTeam = createSampleTeam(1L);
//        when(teamRepository.findById(1L)).thenReturn(Optional.of(mockTeam));
//
//        Optional<Team> result = teamService.getTeamById(1L);
//        assertTrue(result.isPresent());
//        assertEquals("Team Alpha", result.get().getName());
//    }
//
//    @Test
//    void testGetTeamById_invalidId() {
//        assertThrows(IllegalArgumentException.class, () -> teamService.getTeamById(-1L));
//        assertThrows(IllegalArgumentException.class, () -> teamService.getTeamById(null));
//    }
//
//    @Test
//    void testCreateTeam_valid() {
//        Team newTeam = createSampleTeam(null);
//        when(teamRepository.existsByName("Team Alpha")).thenReturn(false);
//        when(teamRepository.save(any(Team.class))).thenAnswer(invocation -> {
//            Team saved = invocation.getArgument(0);
//            saved.setId(1L);
//            return saved;
//        });
//
//        Team created = teamService.createTeam(newTeam);
//        assertNotNull(created.getId());
//        verify(teamRepository).save(newTeam);
//    }
//
//    @Test
//    void testCreateTeam_duplicateName() {
//        Team newTeam = createSampleTeam(null);
//        when(teamRepository.existsByName("Team Alpha")).thenReturn(true);
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> teamService.createTeam(newTeam));
//        assertTrue(ex.getMessage().contains("already exists"));
//    }
//
//    @Test
//    void testDeleteTeam_validId() {
//        when(teamRepository.existsById(1L)).thenReturn(true);
//        doNothing().when(teamRepository).deleteById(1L);
//
//        teamService.deleteTeam(1L);
//        verify(teamRepository).deleteById(1L);
//    }
//
//    @Test
//    void testDeleteTeam_nonExistingId() {
//        when(teamRepository.existsById(2L)).thenReturn(false);
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> teamService.deleteTeam(2L));
//        assertTrue(ex.getMessage().contains("not found"));
//    }
//}
