import { AppProvider, useApp } from './context/AppContext';
import { BottomNav } from './components/layout/BottomNav';
import { NotificationsDrawer } from './components/layout/NotificationsDrawer';
import { WelcomeScreen } from './components/screens/WelcomeScreen';
import { OnboardingFlow } from './components/screens/OnboardingFlow';
import { GeneratingScreen } from './components/screens/GeneratingScreen';
import { ResultScreen } from './components/screens/ResultScreen';
import { HomeScreen } from './components/screens/HomeScreen';
import { ProtocolScreen } from './components/screens/ProtocolScreen';
import { InsightsScreen } from './components/screens/InsightsScreen';
import { StackScreen } from './components/screens/StackScreen';
import { ProProfileScreen } from './components/screens/ProProfileScreen';
import { BioWiki } from './components/screens/BioWiki';

function AppShell() {
  const { state } = useApp();

  if (state.phase === 'welcome') return <WelcomeScreen />;
  if (state.phase === 'onboarding') return <OnboardingFlow />;
  if (state.phase === 'generating') return <GeneratingScreen />;
  if (state.phase === 'result') return <ResultScreen />;

  return (
    <div className="relative mx-auto min-h-[100dvh] max-w-md bg-canvas text-stone-900 dark:bg-stone-950 dark:text-stone-100">
      {/* Accessibility live region for polite announcements */}
      <div aria-live="polite" aria-atomic="true" id="a11y-live" className="sr-only" />
      {state.navTab === 'home' && <HomeScreen />}
      {state.navTab === 'protocol' && <ProtocolScreen />}
      {state.navTab === 'insights' && <InsightsScreen />}
      {state.navTab === 'stack' && <StackScreen />}
      {state.navTab === 'pro' && <ProProfileScreen />}
      <BottomNav />
      <NotificationsDrawer />
      {state.uiOverlay === 'wiki' && <BioWiki />}
    </div>
  );
}

export default function App() {
  return (
    <AppProvider>
      <AppShell />
    </AppProvider>
  );
}
